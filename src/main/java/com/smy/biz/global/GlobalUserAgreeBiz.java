package com.smy.biz.global;

/**
 * @Description: 好友请求表
 * @Author: lwt
 * @CreateDate: 2018/9/6
 * @Version: 1.0
 */
public interface GlobalUserAgreeBiz {

    /**
      * @Description:  查询是否存在好友关系，如果存在返回返回id
      * @Pramers:      user_id  用户id
      * @Pramers:      rec_user  关联用户id
      * @return:       Long
     */
    public Long isExistRelation(long user_id,long rec_user);

    /**
     * @Description:  将两个用户互相添加为好友
     * @Pramers:      user_id  用户id
     * @Pramers:      rec_user  关联用户id
     * @return:       返回类型
     */
    public boolean addBuddyAction(long user_id,long rec_user);

    /**
      * @Description:  删除记录
      * @Pramers:      agree_id
      * @return:       boolean
     */
    public boolean deleteUserAgreeById(long agree_id);

}