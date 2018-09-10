package com.smy.controller;

import com.smy.biz.friendopt.UserFriendMagBiz;
import com.smy.biz.global.GlobalUserAgreeBiz;
import com.smy.model.UserAgree;
import com.zhuoan.dto.Dto;
import com.zhuoan.util.DateUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 测试类
 * @Author: lwt
 * @CreateDate: 2018/9/6
 * @Version: 1.0
 */
@Controller
@RequestMapping("/test")
public class test {
    @Resource
    UserFriendMagBiz user_friend;
    @Resource
    GlobalUserAgreeBiz user_agree;

    /**
      * @Description:  申请添加好友
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/addapply.html")
    public void testAddApply(HttpServletResponse response, HttpServletRequest request) throws IOException {
        UserAgree ua=new UserAgree();
        ua.setUserId(3L);
        ua.setSrcId(1L);
        ua.setReason("测试请求");
        ua.setCreateTime(DateUtils.gettimestamp());
        ua.setFriendFrom("0");
        ua.setType(1);

        boolean effo= user_friend.addNewFriends(ua);

        Dto.printMsg(response, "申请添加好友结果："+effo);
    }


    /**
      * @Description:  获取我的申请列表
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/getapply.html")
    public void testGetApply(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String user_id=request.getParameter("id");
        String now_page=request.getParameter("page");
        JSONObject obj=JSONObject.fromObject(user_agree.getUserApplyList(Long.valueOf(user_id),Integer.valueOf(now_page)));
        Dto.printMsg(response, "用户："+user_id+"的申请列表结果:"+obj.toString());
    }

    /**
      * @Description:  同意添加好友
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/accept.html")
    public void acceptOpt(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String agree_id=request.getParameter("agree_id");
        String type=request.getParameter("type");
        boolean effo=user_friend.addFriendRequestProcess(Long.valueOf(agree_id),Integer.valueOf(type));
        Dto.printMsg(response, "申请记录："+agree_id+"的结果:"+effo);
    }


    /**
      * @Description:  删除好友
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/deletefriend.html")
    public void deleteFriend(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String agree_id=request.getParameter("agree_id");
        String type=request.getParameter("type");
        boolean effo=user_friend.addFriendRequestProcess(Long.valueOf(agree_id),Integer.valueOf(type));
        Dto.printMsg(response, "申请记录："+agree_id+"的结果:"+effo);
    }



}
