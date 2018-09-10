package com.smy.biz.global.impl;

import com.smy.biz.global.GlobalBaseUsersBiz;
import com.zhuoan.ssh.dao.SSHUtilDao;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: 用户表
 * @Author: lwt
 * @CreateDate: 2018/9/6
 * @Version: 1.0
 */
@Service
@Transactional
public class GlobalBaseUsersBizImpl implements GlobalBaseUsersBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public JSONObject getUserByIdOrChainAdd(String user_id) {
        String sql="";
        try {
            Long userId=Long.valueOf(user_id);
            sql="select id,chan_add,create_time,memo from base_users where id=?";
        }catch (NumberFormatException e){
            sql="select id,chan_add,create_time,memo from base_users where chan_add=?";
        }finally {
            Object[] par={user_id};
            JSONObject obj= JSONObject.fromObject(dao.getObjectBySQL(sql,par));
            return obj;
        }
    }
}
