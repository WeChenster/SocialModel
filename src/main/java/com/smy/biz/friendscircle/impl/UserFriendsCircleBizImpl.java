package com.smy.biz.friendscircle.impl;


/**
 * @Description: 用户动态操作
 * @Author: lwt
 * @CreateDate: 2018/9/10
 * @Version: 1.0
 */

import com.smy.biz.friendscircle.UserFriendsCircleBiz;
import com.smy.biz.global.*;
import com.smy.model.UserComments;
import com.smy.model.UserDynMsg;
import com.smy.model.UserTimeStamp;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import com.zhuoan.util.DateUtils;
import com.zhuoan.util.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

@Service
@Transactional
public class UserFriendsCircleBizImpl implements UserFriendsCircleBiz {
    @Resource
    SSHUtilDao dao;
    @Resource
    GlobalBaseUsersBiz base_users;
    @Resource
    GlobalUserDynMsgBiz user_dyn_msg;
    @Resource
    GlobalUserTimeStampBiz user_time_stamp;
    @Resource
    GlobalUserFriendsBiz user_friends;
    @Resource
    GlobalUserCommentsBiz user_comments;

    @Override
    public Long insertUserDynamicMessage(UserDynMsg userDynMsg) {
        //1、插入广场消息表
        Long msg_id=user_dyn_msg.insertUserDynMsg(userDynMsg);

        if(msg_id!=null){
            //2、在当前用户的时间轴上插入一条记录（is_main=1）
            UserTimeStamp userTimeStamp=new UserTimeStamp();
            userTimeStamp.setUserId(userDynMsg.getUserId());
            userTimeStamp.setUdmsgId(msg_id);
            userTimeStamp.setCreateTime(DateUtils.gettimestamp());
            userTimeStamp.setIsMine(Dto.ALL_TRUE);
            userTimeStamp.setIdDel(Dto.ALL_FALSE);
            Long uts_id=user_time_stamp.insertLine(userTimeStamp);

            if(uts_id!=null){
                //3、判断是否陌生人可见，触发时间轴（用户ID，消息ID, 是否陌生人可见）
                JSONArray array;
                if(userDynMsg.getIsStrange()==Dto.ALL_TRUE){
                    //陌生人可见  系统所有用户
                    array= base_users.getAllUsers();
                }else{
                    //只 好友可见
                    array=user_friends.getUserFriendsByUserId(userDynMsg.getUserId());
                }
                //遍历列表为该列表下的用户朋友圈时间轴中添加一条记录
                for (int i = 0; i < array.size(); i++) {
                    JSONObject ob=array.getJSONObject(i);
                    long uid=ob.getLong("id");
                    UserTimeStamp uts=new UserTimeStamp();
                    uts.setUserId(uid);
                    uts.setUdmsgId(msg_id);
                    uts.setCreateTime(DateUtils.gettimestamp());
                    uts.setIsMine(Dto.ALL_FALSE);
                    uts.setIdDel(Dto.ALL_FALSE);
                    Long uts_id2=user_time_stamp.insertLine(uts);
                    Dto.writeLog("第"+i+"位用户时间轴插入结果："+uts_id2);
                    if(uts_id2==null){
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return msg_id;
                    }
                }
                return null;
            }else{
                Dto.writeLog("插入朋友圈时间轴结果："+uts_id);
                return null;
            }
        }else{
            Dto.writeLog("插入动态表结果："+msg_id);
            return null;
        }
    }

    @Override
    public JSONObject getMyDynamicMessage(JSONObject in) {
        JSONObject obj=new JSONObject();
        JSONArray array=new JSONArray();

        JSONObject utsObj = user_time_stamp.getUserTimeStampListByUserId(in.getLong("user_id"), in.getInt("size"), in.getInt("now_page"));
        JSONArray arr=JSONArray.fromObject(utsObj.get("array"));
        for (int i = 0; i < arr.size(); i++) {
            JSONObject ob = arr.getJSONObject(i);
            long msg_id = ob.getLong("udmsg_id");//动态消息id

            JSONObject udm = JSONObject.fromObject(user_dyn_msg.getUserDynMsgById(msg_id));
            udm= TimeUtil.transTimeStamp(udm,"yyyy-MM-dd HH:mm:ss","createTime");
            array.add(i, udm);
        }
        utsObj.element("array",array);

        obj.put("code",Dto.ALL_TRUE);
        obj.put("msg","获取成功");
        obj.put("data",utsObj);
        return obj;
    }

    @Override
    public boolean forwardUserDynamicMessage(UserDynMsg userDynMsg) {
        //插入一条广场消息表
        Long msg_id= insertUserDynamicMessage(userDynMsg);

        //更新转发动态数
        boolean effo1=user_dyn_msg.updateColumnValue(" forward_num = forward_num + 1 ",userDynMsg.getMsgId());

        //插入一条转发类型记录
        UserComments uc=new UserComments();
        uc.setImg(msg_id);
        uc.setUserId(userDynMsg.getUserId());
        uc.setRecUser(0L);
        uc.setType(Dto.USER_COMMENT_TYPE_FORWRAD);
        uc.setIdDel(Dto.ALL_FALSE);
        uc.setCreateTime(DateUtils.gettimestamp());

        Long uc_id=user_comments.insertLines(uc);

        if(msg_id!=null&&effo1&&uc_id!=null){
            return true;
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean praiseUserDynamicMessage(UserComments userComments) {
        //查询 用户是否点赞消息  如果有就删除 如果没有就添加一条记录
        UserDynMsg userDynMsg= user_dyn_msg.getUserDynMsgById(userComments.getImg());

        JSONObject uc=user_comments.isPraise(userDynMsg.getId(),userDynMsg.getUserId(),Dto.USER_COMMENT_TYPE_PRAISE);
        if(!uc.isNullObject()&&uc.containsKey("id")&&!uc.getString("id").equals("null")){ //取消点赞
            //更新消息点赞数
            boolean effo1=user_dyn_msg.updateColumnValue(" good_num = good_num - 1 ",userDynMsg.getMsgId());

            //删除点赞记录
            boolean effo2=user_comments.deleteById(uc.getLong("id"));

            if(effo1&&effo2){
                return true;
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }else{//点赞操作
            //更新消息点赞数
            boolean effo_1=user_dyn_msg.updateColumnValue(" good_num = good_num + 1 ",userDynMsg.getMsgId());

            Long effo_2=user_comments.insertLines(userComments);

            if(effo_1&&effo_2!=null){
                return true;
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
    }

    @Override
    public boolean commentUserDynamicMessage(UserComments userComments) {
        //更新消息表中的评论数
        boolean effo1=user_dyn_msg.updateColumnValue(" comm_num = comm_num + 1 ",userComments.getImg());

        //添加评论记录
        Long effo_2=user_comments.insertLines(userComments);
        if(effo1&&effo_2!=null){
            return true;
        }else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteUserDynamicMessage(long udmsg_id) {

        //1、删除广场消息表
        boolean effo=user_dyn_msg.deleteLineById(udmsg_id);

        //2、删除朋友圈 时间轴相关消息记录
        boolean effo1=user_time_stamp.deleteByMsgId(udmsg_id);

        //3、删除该消息的所有评论记录
        boolean effo2=user_comments.deleteByMsgId(udmsg_id);
        if(effo&&effo1&&effo2){
            return true;
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean insertCommentUserDynamicMessage(UserComments userComments) {
        //判断评论表类型
        if(userComments.getType()==Dto.USER_COMMENT_TYPE_PRAISE){//点赞
            boolean praise_result=praiseUserDynamicMessage(userComments);
            return praise_result;
        }else if(userComments.getType()==Dto.USER_COMMENT_TYPE_COM){//论评
            boolean comment_result= commentUserDynamicMessage(userComments);
            return comment_result;
        }else{
            return false;
        }
    }
}
