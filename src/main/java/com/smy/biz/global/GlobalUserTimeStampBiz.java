package com.smy.biz.global;

import com.smy.model.UserTimeStamp;

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
}
