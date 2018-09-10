package com.smy.biz.friendopt;

import com.smy.model.UserAgree;
import com.smy.model.UserFriends;

/**
 * @Description: 用户体系接口
 * @Author: lwt
 * @CreateDate: 2018/9/5
 * @Version: 1.0
 */
public interface UserFriendMagBiz {

    /**
      * @Description:  请求添加好友
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public boolean addNewFriends(UserAgree userAgree);

    /**
      * @Description:  同意好友请求操作
      * @Pramers:      agree_id   好友请求消息ID
      * @Pramers:      type   处理类型（同意）
      * @return:       返回类型
     */
    public boolean addFriendRequestProcess(long agree_id,int type);

    /**
      * @Description:  修改好友设置
      * @Pramers:      userFriends
      * @return:       返回类型
     * 1.	判断该好友是否加入黑名单或者删除黑名单（注：要关联朋友圈时间轴）
     */
    public boolean updateFriendSetting(UserFriends userFriends);

    /**
      * @Description:  删除好友
      * @Pramers:      user_id   用户
      * @Pramers:      rec_user   指定删除用户
      * @return:       返回类型
     */
    public boolean deleteFriend(long user_id, long rec_user);


    /**
      * @Description:  通过用户和好友获取记录id
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public Long getFriendRelsId(long user_id, long rec_user);
}
