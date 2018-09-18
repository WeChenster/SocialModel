package com.smy.biz.global.impl;
/**
 * @Description: 评论表
 * @Author: lwt
 * @CreateDate: 2018/9/12
 * @Version: 1.0
 */

import com.smy.biz.global.GlobalUserCommentsBiz;
import com.smy.model.UserComments;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class GlobalUserCommentsBizImpl implements GlobalUserCommentsBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public boolean deleteById(long uc_id) {
        String sql="update user_commects set id_del=? where id=?";
        Object[] par ={Dto.ALL_TRUE,uc_id};
        return dao.updObjectBySQL(sql,par);
    }

    @Override
    public boolean deleteByMsgId(long udmsg_id) {
        String sql="update user_comments set id_del=? where img=?";
        Object[] par={Dto.ALL_TRUE,udmsg_id};
        return dao.updObjectBySQL(sql,par);
    }

    @Override
    public Long insertLines(UserComments userComments) {
        return (Long)dao.saveObject(userComments);
    }

    @Override
    public JSONObject isPraise(long msg_id, long user_id, int type){
        String sql="select id from user_comments where img=? and user_id=? and type=? and id_del=?";
        Object[] par={msg_id,user_id,type,Dto.ALL_FALSE};
        return JSONObject.fromObject(dao.getObjectBySQL(sql,par));
    }
}
