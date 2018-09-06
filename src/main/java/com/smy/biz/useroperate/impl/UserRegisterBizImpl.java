package com.smy.biz.useroperate.impl;

import com.smy.biz.useroperate.UserRegisterBiz;
import com.smy.model.BaseUsers;
import com.zhuoan.ssh.dao.SSHUtilDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/5
 * @Version: 1.0
 */
@Service
@Transactional
public class UserRegisterBizImpl implements UserRegisterBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public boolean addUserForRegister(BaseUsers baseUsers) {
        Object obj= dao.saveAsObject(BaseUsers.class,baseUsers);
        if(obj!=null)
            return true;
        else
            return false;
    }
}
