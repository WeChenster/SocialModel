package com.smy.biz.global.impl;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */

import com.smy.biz.global.GlobalUserDynMsgBiz;
import com.smy.model.UserDynMsg;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *@auther:Administrator
 *@Date:2018/9/11
 *@descriotion:
 */
@Service
@Transactional
public class GlobalUserDynMsgBizImpl implements GlobalUserDynMsgBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public Long insertUserDynMsg(UserDynMsg userDynMsg) {
        Long id =(Long)dao.saveObject(userDynMsg);
        return id;
    }

    @Override
    public UserDynMsg getUserDynMsgById(Long msg_id) {
        UserDynMsg userDynMsg= (UserDynMsg)dao.getObjectById(UserDynMsg.class,msg_id);
        return userDynMsg;
    }

    @Override
    public boolean updateColumnValue(String column_value, long udmsg_id) {
        String sql="update user_dyn_msg set ? where id=?";
        Object[] par={column_value,udmsg_id};
        return dao.updObjectBySQL(sql,par);
    }

    @Override
    public boolean deleteLineById(long msg_id) {
        String sql="update user_dyn_msg set id_del=? where id=?";
        Object[] par ={Dto.ALL_TRUE,msg_id};
        return dao.updObjectBySQL(sql,par);
    }
}
