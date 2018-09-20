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
    public Long addUserForRegister(BaseUsers baseUsers) {
        Long obj= (Long)dao.saveObject(baseUsers);
        return obj;
    }
}
