package com.smy.biz.global.impl;

import com.smy.biz.global.GlobalUserAgreeBiz;
import com.smy.model.UserAgree;
import com.smy.model.UserFriends;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import com.zhuoan.util.DateUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @Description: 好友请求表
 * @Author: lwt
 * @CreateDate: 2018/9/6
 * @Version: 1.0
 */

@Service
@Transactional
public class GlobalUserAgreeBizImpl implements GlobalUserAgreeBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public Long isExistRelation(long user_id, long rec_user) {
        String sql="select id from user_agree where user_id=? and src_id=?";
        Object[] par={user_id,rec_user};
        JSONObject agree=JSONObject.fromObject(dao.getObjectBySQL(sql,par));
        if(!agree.isNullObject()&&agree.containsKey("id")&&!agree.getString("id").equals("null")){
            return agree.getLong("id");
        }else{
            return null;
        }
    }

    @Override
    public boolean addBuddyAction(long user_id, long rec_user) {
        UserFriends u1=new UserFriends();
        u1.setUserId(user_id);
        u1.setRecUser(rec_user);

        u1.setCreateTime(DateUtils.gettimestamp());
        u1.setTop(Dto.ALL_FALSE);
        u1.setMsgNot(Dto.ALL_FALSE);
        u1.setHideHer(Dto.ALL_FALSE);
        u1.setHideMe(Dto.ALL_FALSE);
        u1.setIsBlacklist(Dto.ALL_FALSE);
        Long u1Rs=(Long)dao.saveObject(u1);
        if(u1Rs!=null){
            u1.setUserId(rec_user);
            u1.setRecUser(user_id);
            Long u2Rs=(Long)dao.saveObject(u1);
            if(u2Rs!=null){
                return  true;
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteUserAgreeById(long agree_id) {
        return dao.delObjectById(UserAgree.class,agree_id);
    }
}
