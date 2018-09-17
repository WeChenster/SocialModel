package com.smy.biz.global;

import com.smy.model.UserComments;
import net.sf.json.JSONObject;

/**
 * @Description: 评论表单表操作
 * @Author: lwt
 * @CreateDate: 2018/9/12
 * @Version: 1.0
 */
public interface GlobalUserCommentsBiz {

    /**
      * @Description:  删除记录
      * @Pramers:      uc_id
      * @return:       返回类型
     */
    public boolean deleteById(long uc_id);

    /**
      * @Description:  删除该消息所有的评论以及相关记录
      * @Pramers:      udmsg_id  动态id
      * @return:       返回类型
     */
    public boolean deleteByMsgId(long udmsg_id);

    /**
      * @Description:  插入一条记录
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public Long insertLines(UserComments userComments);

    /**
      * @Description:  判断该用户是否已经点赞该消息
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public JSONObject isPraise(long msg_id, long user_id, int type);

}
