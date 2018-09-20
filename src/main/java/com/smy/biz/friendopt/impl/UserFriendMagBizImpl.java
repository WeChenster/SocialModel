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
    public Long addNewFriends(UserAgree userAgree) {
        Long MsgId=(Long)dao.saveObject(userAgree);
        //TODO:客户点发送消息到被添加的用户,发消息给user_id
        return MsgId;
    }

    @Override
    public boolean addFriendRequestProcess(long agree_id, int type) {
        //获取该消息记录
        UserAgree useragree= (UserAgree)dao.getObjectById(UserAgree.class,agree_id);
        if(useragree!=null){
            //判断处理类型
            if(type==Dto.FRIENDS_APPLE_TYPE_ACCEPT){//类型为：接受
                //1、添加好友操作
                boolean effo=user_agree.addBuddyAction(useragree.getUserId(),useragree.getSrcId());

                //2、修改该消息记录的处理类型
                useragree.setType(Dto.FRIENDS_APPLE_TYPE_ADDED);//设置类型为已添加好友
                boolean effo1=dao.updObject(useragree);

                if(effo&&effo1){
                    //TODO:客户端发送消息到被添加的用户：已成为好友可以互相聊天
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
        String sql="select id from user_friends where user_id=? and rec_user=? and id_del=?";
        Object[] par ={user_id,rec_user,Dto.ALL_FALSE};
        JSONObject obj=JSONObject.fromObject(dao.getObjectBySQL(sql,par));
        if(!obj.isNullObject()&&obj.containsKey("id")&&!obj.getString("id").equals("null")){
            return obj.getLong("id");
        }else{
            return null;
        }
    }


    @Override
    public boolean deleteFriend(long user_id, long rec_user) {
        //查询好友关系是否存在
        Long Fd1=this.getFriendRelsId(user_id,rec_user);
        Long Fd2=this.getFriendRelsId(rec_user,user_id);

        if(Fd1!=null&&Fd2!=null){
            //删除好友关系
            boolean fd1=deleteSingleFriend(Fd1);
            boolean fd2=deleteSingleFriend(Fd2);

            //查询还有申请记录
            Long Rel_1= user_agree.isExistRelation(user_id,rec_user);
            Long Rel_2= user_agree.isExistRelation(rec_user,user_id);
            boolean ug1=true;
            boolean ug2=true;
            //1、删除好友申请记录
            if(Rel_1!=null){
                ug1=user_agree.deleteUserAgreeById(Rel_1);
            }
            if(Rel_2!=null){
                ug2=user_agree.deleteUserAgreeById(Rel_2);
            }

            //TODO:触发朋友圈时间轴  删除好友动态

            if(fd1&&fd2&&ug1&&ug2){
                return true;
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }else{
            return false;
        }
    }

    /**
      * @Description:  删除单个好友
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    private boolean deleteSingleFriend(long friend_id){
        String sql="update user_friends set id_del=? where id=?";
        Object[] par ={Dto.ALL_TRUE,friend_id};
        return dao.updObjectBySQL(sql,par);
    }
}
