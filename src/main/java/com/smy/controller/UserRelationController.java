package com.smy.controller;

import com.smy.biz.friendopt.UserFriendMagBiz;
import com.smy.biz.global.GlobalBaseUsersBiz;
import com.smy.biz.global.GlobalUserAgreeBiz;
import com.smy.biz.global.GlobalUserFriendsBiz;
import com.smy.model.UserAgree;
import com.zhuoan.dto.Dto;
import com.zhuoan.util.CheckTypeUtil;
import com.zhuoan.util.DateUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 用户关系体系
 * @Author: lwt
 * @CreateDate: 2018/9/18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/socialmode")
public class UserRelationController {
    @Resource
    GlobalBaseUsersBiz base_user;

    @Resource
    GlobalUserAgreeBiz user_agree;
    @Resource
    UserFriendMagBiz user_friend_mag;
    @Resource
    GlobalUserFriendsBiz user_friend;


    /**获取好友申请记录
      * @Description:  传入用户区块获取该用户的好友申请记录，进行操作
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/getfriendagree.html")
    public void getFriendAgree(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject obj=new JSONObject();
        String chain_add=request.getParameter("chain_add");
        int now_page=(request.getParameter("now_page")!=null&&!request.getParameter("now_page").equals(""))?Integer.valueOf(request.getParameter("now_page")):1;
        if(chain_add!=null&&!chain_add.equals("")){
            JSONObject user=base_user.getUserByIdOrChainAdd(chain_add);
            if(user!=null&&user.containsKey("id")&&!user.getString("id").equals("null")){
                JSONObject data=user_agree.getUserApplyList(user.getLong("id"),now_page);
                obj.put("code", Dto.ALL_TRUE);
                obj.put("msg", "获取成功");
                obj.put("data", data);
            }else{
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "用户不存在");
            }
        }else{
            obj.put("code", Dto.ALL_FALSE);
            obj.put("msg", "用户区块不能为空");
        }
        Dto.printMsg(response,obj.toString());
    }


    /**发送好友申请
      * @Description:  查找好友后，需要发送好友申请，等待对方同意。
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/addfriendagree.html")
    public void addFriendAgree(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject obj=new JSONObject();
        String chain_add=request.getParameter("chain_add");//用户区块
        String rec_chain=request.getParameter("rec_chain");//来源用户区块
        String friend_from=request.getParameter("friend_from");//来源类型  0：通讯录  1：查找
        String reason=request.getParameter("reason");//添加理由
        if(chain_add!=null&&!chain_add.equals("")&&
                rec_chain!=null&&!rec_chain.equals("")&&
                friend_from!=null&&!friend_from.equals("")){
            if(!CheckTypeUtil.isNumber(friend_from)){
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "来源类型必须是数字");
                Dto.printMsg(response,obj.toString());
                return;
            }
             JSONObject user =base_user.getUserByIdOrChainAdd(chain_add);
             JSONObject rec_user =base_user.getUserByIdOrChainAdd(rec_chain);
             if(!user.isNullObject()&&user.containsKey("id")&&!user.getString("id").equals("null")){
                if(!rec_user.isNullObject()&&rec_user.containsKey("id")&&!rec_user.getString("id").equals("null")){
                    //组织好友申请对象
                    UserAgree usera=new UserAgree();
                    usera.setUserId(user.getLong("id"));
                    usera.setSrcId(rec_user.getLong("id"));
                    usera.setReason(reason!=null?reason:"");
                    usera.setCreateTime(DateUtils.gettimestamp());
                    usera.setFriendFrom(friend_from);
                    usera.setType(Dto.FRIENDS_APPLE_TYPE_ACCEPT);
                    usera.setIdDel(Dto.ALL_FALSE);

                    Long apply_msg=user_friend_mag.addNewFriends(usera);
                    if(apply_msg!=null){
                        obj.put("code", Dto.ALL_TRUE);
                        obj.put("msg", "操作成功");
                    }else{
                        obj.put("code", Dto.ALL_FALSE);
                        obj.put("msg", "操作失败");
                    }
                }else{
                    obj.put("code", Dto.ALL_FALSE);
                    obj.put("msg", "来源用户不存在");
                }
             }else{
                 obj.put("code", Dto.ALL_FALSE);
                 obj.put("msg", "用户不存在");
             }
        }else{
            obj.put("code", Dto.ALL_FALSE);
            obj.put("msg", "请将输入必填字段");
        }
        Dto.printMsg(response,obj.toString());
    }


    /**
      * @Description:  接受好友申请
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/acceptfriendagree.html")
    public void acceptFriendAgree(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject obj=new JSONObject();
        String agree_id=request.getParameter("agree_id");
        String chain_add=request.getParameter("chain_add");
        if(agree_id!=null&&!agree_id.equals("")&&chain_add!=null&&!chain_add.equals("")){
            if(!CheckTypeUtil.isNumber(agree_id)){
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "好友记录必须是整数");
                Dto.printMsg(response,obj.toString());
                return;
            }
            boolean effo=user_friend_mag.addFriendRequestProcess(Long.valueOf(agree_id),Dto.FRIENDS_APPLE_TYPE_ACCEPT);
            if(effo){
                obj.put("code", Dto.ALL_TRUE);
                obj.put("msg", "操作成功");
            }else{
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "操作失败");
            }
        }else{
            obj.put("code", Dto.ALL_FALSE);
            obj.put("msg", "请将输入必填字段");
        }
        Dto.printMsg(response,obj.toString());
    }

    /**
      * @Description:  获取好友列表
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/getuserfriends.html")
    public void getUserFriends(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject obj=new JSONObject();
        String chain_add=request.getParameter("chain_add");
        int now_page=request.getParameter("now_page")!=null?Integer.valueOf(request.getParameter("now_page")):1;
        if(chain_add!=null&&!chain_add.equals("")){
            JSONObject us=base_user.getUserByIdOrChainAdd(chain_add);
            if(!us.isNullObject()&&us.containsKey("id")&&!us.getString("id").equals("null")){
                JSONObject result=user_friend.getUserFriendsByUserId(us.getLong("id"),now_page);
                obj.put("code", Dto.ALL_TRUE);
                obj.put("msg", "获取成功");
                obj.put("data",result);
            }else{
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "用户不存在");
            }
        }else{
            obj.put("code", Dto.ALL_FALSE);
            obj.put("msg", "请将输入必填字段");
        }
        Dto.printMsg(response,obj.toString());
    }

    /**
      * @Description:  获取好友设置信息
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/getuserfriendsetting.html")
    public void getUserFriendSetting(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject obj=new JSONObject();
        String chain_add=request.getParameter("chain_add");
        String ufd_id=request.getParameter("ufd_id");
        if(chain_add!=null&&!chain_add.equals("")&&ufd_id!=null&&!ufd_id.equals("")){
            if(CheckTypeUtil.isNumber(ufd_id)){


            }else{
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "好友记录必须是整数");
            }
        }else{
            obj.put("code", Dto.ALL_FALSE);
            obj.put("msg", "请将输入必填字段");
        }
        Dto.printMsg(response,obj.toString());
    }


    /**
      * @Description:  修改好友设置信息
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/updateuserfriendsetting.html")
    public void updateUserFriendSetting(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }

    /**删除好友
      * @Description:  删除好友申请记录以及好友关系，并且在朋友圈中也删除该好友的所有动态
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/deleteuserfriend.html")
    public void deleteUserFriend(HttpServletResponse response, HttpServletRequest request) throws IOException {

    }
}
