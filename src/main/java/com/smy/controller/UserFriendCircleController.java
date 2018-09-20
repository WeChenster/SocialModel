package com.smy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @Description: 用户朋友圈
 * @Author: lwt
 * @CreateDate: 2018/9/18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/socialmode")
public class UserFriendCircleController {

    /**发布动态
      * @Description:  发布一条动态到我的朋友圈，并同步到好友的朋友圈。
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/insertdynamic.html")
    public void insertDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  获取朋友圈动态列表
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/getdynamic.html")
    public void getDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  转发动态
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/forwarddynamic.html")
    public void forwardDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  点赞动态
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/praiseddynamic.html")
    public void praisedDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  获取评论列表
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/getcommentlist.html")
    public void getCommentlist(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  评论动态
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/commentdynamic.html")
    public void commentDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  评论回复
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/replycommentdynamic.html")
    public void replyCommentDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  删除评论/删除评论回复
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/deletecomment.html")
    public void deleteComment(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**
      * @Description:  删除动态
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/deletedynamic.html")
    public void deleteDynamic(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

}
