package com.smy.biz.global;

import com.smy.model.UserTimeStamp;
import net.sf.json.JSONObject;

/**
 * @Description: 朋友圈时间轴单表操作
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */
public interface GlobalUserTimeStampBiz {

    /**
      * @Description:  插入一条记录
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public Long insertLine(UserTimeStamp userTimeStamp);

    /**
      * @Description:  获取用户时间轴列表
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    public JSONObject getUserTimeStampListByUserId(long user_id, int size, int now_page);

    /**
      * @Description: 通过消息记录删除时间轴记录
      * @Pramers:      udmsg_id
      * @return:       返回类型
     */
    public boolean deleteByMsgId(long udmsg_id);
}
