package com.smy.biz.global;

import net.sf.json.JSONObject;

/**
 * @Description: 用户表
 * @Author: lwt
 * @CreateDate: 2018/9/6
 * @Version: 1.0
 */
public interface GlobalBaseUsersBiz {

    /**
      * @Description:  通过区块地址用户id 获取用户信息
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public JSONObject getUserByIdOrChainAdd(String user_id);

}
