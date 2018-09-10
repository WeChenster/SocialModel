package com.smy.biz.friendopt.impl;

import com.smy.biz.friendopt.UserFriendMagBiz;
import com.smy.biz.global.GlobalBaseUsersBiz;
import com.smy.biz.global.GlobalUserAgreeBiz;
import com.smy.model.UserAgree;
import com.smy.model.UserFriends;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @Description: 用户体系接口Impl
 * @Author: lwt
 * @CreateDate: 2018/9/5
 * @Version: 1.0
 */
@Service
@Transactional
public class UserFriendMagBizImpl implements UserFriendMagBiz {
    @Resource
    SSHUtilDao dao;
    @Resource
    GlobalBaseUsersBiz base_users;
    @Resource
    GlobalUserAgreeBiz user_agree;


    @Override
    public boolean addNewFriends(UserAgree userAgree) {
        //查询好友是否存在
        JSONObject effo=base_users.getUserByIdOrChainAdd(String.valueOf(userAgree.getUserId()));
        if(!effo.isNullObject()&&effo.containsKey("id")&&!effo.getString("id").equals("null")){
            Long MsgId=(Long)dao.saveObject(userAgree);

            //TODO:发送消息到被添加的用户,发消息给user_id
            if(MsgId!=null)
                return  true;
            else
                return false;
        }else
            return false;
    }

    @Override
    public boolean addFriendRequestProcess(long agree_id, int type) {
        //获取该消息记录
        UserAgree useragree= (UserAgree)dao.getObjectById(UserAgree.class,agree_id);
        if(useragree!=null){
            //判断处理类型
            if(type==Dto.FRIENDS_APPLE_TYPE_ACCEPT){//同意添加为好友
                //1、添加好友操作
                boolean effo=user_agree.addBuddyAction(useragree.getUserId(),useragree.getSrcId());

                //2、修改该消息记录的处理类型
                useragree.setType(Dto.FRIENDS_APPLE_TYPE_ADDED);//设置类型为已添加好友
                boolean effo1=dao.updObject(useragree);

                if(effo&&effo1){
                    //TODO:发送消息到被添加的用户：已成为好友可以互相聊天
                    return true;
                }else{
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }else{
                return false;
            }
        }else{
            Dto.writeLog("好友请求表记录："+agree_id+"不存在");
            return  false;
        }
    }

    @Override
    public boolean updateFriendSetting(UserFriends userFriends) {
        //待定
        return false;
    }

    @Override
    public Long getFriendRelsId(long user_id, long rec_user){
        String sql="select id from user_friends where user_id=? and rec_user=?";
        Object[] par ={user_id,rec_user};
        JSONObject obj=JSONObject.fromObject(dao.getObjectBySQL(sql,par));
        if(!obj.isNullObject()&&obj.containsKey("id")&&!obj.getString("id").equals("null")){
            return obj.getLong("id");
        }else{
            return null;
        }
    }


    @Override
    public boolean deleteFriend(long user_id, long rec_user) {
        Long Rel_1= user_agree.isExistRelation(user_id,rec_user);
        Long Rel_2= user_agree.isExistRelation(rec_user,user_id);
        if(Rel_1!=null&&Rel_2!=null){
            //1、删除好友申请记录
            boolean ug1=user_agree.deleteUserAgreeById(Rel_1);
            boolean ug2=user_agree.deleteUserAgreeById(Rel_2);

            //查询好友关系是否存在
            Long Fd1=this.getFriendRelsId(user_id,rec_user);
            Long Fd2=this.getFriendRelsId(user_id,rec_user);

            if(ug1&&ug2){
                if(Fd1!=null&&Fd2!=null){
                    //删除好友关系
                    boolean fd1=deleteSingleFriend(Fd1);
                    boolean fd2=deleteSingleFriend(Fd2);
                    //触发朋友圈时间轴  删除好友动态
                    if(fd1&&fd2){
                        return true;
                    }else{
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return false;
                    }
                }else{
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean deleteSingleFriend(long friend_id){
        return dao.delObjectById(UserFriends.class,friend_id);
    }
}
