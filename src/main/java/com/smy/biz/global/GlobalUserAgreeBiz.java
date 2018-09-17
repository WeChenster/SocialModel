package com.smy.biz.global;

import net.sf.json.JSONObject;

/**
 * @Description: 好友请求表
 * @Author: lwt
 * @CreateDate: 2018/9/6
 * @Version: 1.0
 */
public interface GlobalUserAgreeBiz {

    /**
      * @Description:  获取用户申请列表
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public JSONObject getUserApplyList(long user_id,int now_page);

    /**
      * @Description:  系，如果存在返回查询是否存在好友关返回id
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
      * @Description:  删除记录申请记录
      * @Pramers:      agree_id
      * @return:       boolean
     */
    public boolean deleteUserAgreeById(long agree_id);

}
