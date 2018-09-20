package com.smy.biz.global;

import net.sf.json.JSONObject;

/**
 * @Description: 好友关系表单表操作
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */
public interface GlobalUserFriendsBiz {

    /**
      * @Description:  获取该用户的好友
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public JSONObject getUserFriendsByUserId(long user_id,int now_page);


    /**
      * @Description:  通过好友关系id  获取
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public JSONObject getUserFriendsById(long ufd_id);
}
