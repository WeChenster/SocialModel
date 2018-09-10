package com.smy.biz.friendscircle;

import com.smy.model.UserComments;
import com.smy.model.UserDynMsg;
import net.sf.json.JSONObject;

/**
 * @Description: 好友圈业务接口
 * @Author: lwt
 * @CreateDate: 2018/9/10
 * @Version: 1.0
 */
public interface UserFriendsCircleBiz {

    /**
      * @Description:  发布动态
      * @Pramers:      userDynMsg
      * @return:       返回类型
     */
    public boolean insertUserDynamicMessage(UserDynMsg userDynMsg);

    /**
      * @Description:  获取用户的动态列表
      * @Pramers:      in   {"chain_add":"xxx","now_page":1,"size":10}
      * @return:       返回类型
     */
    public JSONObject getMyDynamicMessage(JSONObject in);


    /**
      * @Description:  转发动态
      * @Pramers:      userDynMsg
      * @return:       返回类型
     */
    public boolean forwardUserDynamicMessage(UserDynMsg userDynMsg);

    /**
      * @Description:  删除动态
      * @Pramers:      udmsg_id   消息ID
      * @return:       返回类型
     */
    public boolean deleteUserDynamicMessage(long udmsg_id);


    /**
      * @Description:  添加评论，点赞
      * @Pramers:      userComments
      * @return:       返回类型
     */
    public boolean commentUserDynamicMessage(UserComments userComments);

}
