package com.smy.biz.global.impl;

import com.smy.biz.global.GlobalUserAgreeBiz;
import com.smy.model.UserFriends;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import com.zhuoan.util.DateUtils;
import net.sf.json.JSONArray;
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
    public JSONObject getUserApplyList(long user_id, int now_page) {
        JSONObject obj=new JSONObject();
        int size=15;
        String sql="select $ from user_agree where user_id=? and id_del=?";
        Object[] par ={user_id,Dto.ALL_FALSE};

        String countsql=sql.replace("$","count(id)");
        String listsql=sql.replace("$"," id,user_id,src_id,reason,create_time,friend_from,type ");

        int total=dao.getCount(countsql,par);
        JSONArray array =JSONArray.fromObject(dao.getObjectListBySQL(listsql,par,now_page,size));
        for (int i = 0; i < array.size(); i++) {

        }

        obj.element("total", total)
                .element("size", size)
                .element("page", now_page)
                .element("array",array);
        return obj;
    }

    @Override
    public Long isExistRelation(long user_id, long rec_user) {
        String sql="select id from user_agree where user_id=? and src_id=? and id_del=?";
        Object[] par={user_id,rec_user,Dto.ALL_FALSE};
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
        u1.setIdDel(Dto.ALL_FALSE);
        Long u1Rs=(Long)dao.saveObject(u1);

        UserFriends u2=new UserFriends();
        u2.setUserId(rec_user);
        u2.setRecUser(user_id);
        u2.setCreateTime(DateUtils.gettimestamp());
        u2.setTop(Dto.ALL_FALSE);
        u2.setMsgNot(Dto.ALL_FALSE);
        u2.setHideHer(Dto.ALL_FALSE);
        u2.setHideMe(Dto.ALL_FALSE);
        u2.setIsBlacklist(Dto.ALL_FALSE);
        u2.setIdDel(Dto.ALL_FALSE);
        Long u2Rs=(Long)dao.saveObject(u2);
        if(u1Rs!=null&&u2Rs!=null){
            return  true;
        }else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteUserAgreeById(long agree_id) {
        String sql="update user_agree set id_del=? where id=?";
        Object[] par ={Dto.ALL_TRUE,agree_id};
        return dao.updObjectBySQL(sql,par);
    }
}
