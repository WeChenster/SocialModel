package com.smy.biz.global;

import com.smy.model.UserDynMsg;

/**
 * @Description: 动态表单表操作
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */
public interface GlobalUserDynMsgBiz {

    /**
      * @Description:  插入一条记录返回ID
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public Long insertUserDynMsg(UserDynMsg userDynMsg);

    /**
      * @Description:  通过id获取对象
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public UserDynMsg getUserDynMsgById(Long msg_id);
}
