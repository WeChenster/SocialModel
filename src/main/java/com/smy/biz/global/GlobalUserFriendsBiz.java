package com.smy.biz.global;

import com.smy.model.UserFriends;
import net.sf.json.JSONArray;

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
    public JSONArray getUserFriendsByUserId(long user_id);


    /**
      * @Description:  通过好友关系id  获取
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public UserFriends getUserFriendsById(long ufd_id);
}
