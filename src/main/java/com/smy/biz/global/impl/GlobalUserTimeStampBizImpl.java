package com.smy.biz.global.impl;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */

import com.smy.biz.global.GlobalUserTimeStampBiz;
import com.smy.model.UserTimeStamp;
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
public class GlobalUserTimeStampBizImpl implements GlobalUserTimeStampBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public Long insertLine(UserTimeStamp userTimeStamp) {
        Long id= (Long)dao.saveObject(userTimeStamp);
        return id;
    }
}
