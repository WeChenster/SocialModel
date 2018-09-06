package com.smy.biz.friendopt.impl;

import com.smy.biz.friendopt.UserFriendMagBiz;
import com.smy.biz.global.GlobalUserAgreeBiz;
import com.smy.model.UserAgree;
import com.smy.model.UserFriends;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
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
    GlobalUserAgreeBiz user_agree;


    @Override
    public boolean addNewFriends(UserAgree userAgree) {
        Long MsgId=(Long)dao.saveObject(userAgree);

        //TODO:发送消息到被添加的用户,发消息给user_id
        if(MsgId!=null)
            return  true;
        else
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
        return false;
    }

    @Override
    public boolean deleteFriend(long user_id, long rec_user) {
        Long Rel_1= user_agree.isExistRelation(user_id,rec_user);
        Long Rel_2= user_agree.isExistRelation(user_id,rec_user);
        if(Rel_1!=null&&Rel_2!=null){
            boolean effo=user_agree.deleteUserAgreeById(Rel_1);
            boolean effo1=user_agree.deleteUserAgreeById(Rel_2);
            if(effo&&effo1)
                return true;
            else
                return false;
        }else{
            return false;
        }
    }
}
