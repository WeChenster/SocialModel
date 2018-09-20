package com.smy.biz.useroperate;

import com.smy.model.BaseUsers;

/**
 * @Description: 用户注册（账户同步）
 * @Author: lwt
 * @CreateDate: 2018/9/5
 * @Version: 1.0
 */
public interface UserRegisterBiz {

    /**
      * @Description:  账户同步（提供给用户模块的api调用该api就能在该模块添加一个用户）
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public Long addUserForRegister(BaseUsers baseUsers);

}
