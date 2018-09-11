package com.smy.biz.friendscircle.impl;


/**
 * @Description: 用户动态操作
 * @Author: lwt
 * @CreateDate: 2018/9/10
 * @Version: 1.0
 */

import com.smy.biz.friendscircle.UserFriendsCircleBiz;
import com.smy.biz.global.GlobalBaseUsersBiz;
import com.smy.biz.global.GlobalUserDynMsgBiz;
import com.smy.biz.global.GlobalUserFriendsBiz;
import com.smy.biz.global.GlobalUserTimeStampBiz;
import com.smy.model.UserComments;
import com.smy.model.UserDynMsg;
import com.smy.model.UserTimeStamp;
import com.zhuoan.dto.Dto;
import com.zhuoan.util.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserFriendsCircleBizImpl implements UserFriendsCircleBiz {

    @Resource
    GlobalBaseUsersBiz base_users;
    @Resource
    GlobalUserDynMsgBiz user_dyn_msg;
    @Resource
    GlobalUserTimeStampBiz user_time_stamp;
    @Resource
    GlobalUserFriendsBiz user_friends;

    @Override
    public boolean insertUserDynamicMessage(UserDynMsg userDynMsg) {
        //1、插入广场消息表
        Long msg_id=user_dyn_msg.insertUserDynMsg(userDynMsg);


        if(msg_id!=null){
            //2、在当前用户的时间轴上插入一条记录（is_main=1）
            UserTimeStamp userTimeStamp=new UserTimeStamp();
            userTimeStamp.setUserId(userDynMsg.getUserId());
            userTimeStamp.setUdmsgId(msg_id);
            userTimeStamp.setCreateTime(DateUtils.gettimestamp());
            userTimeStamp.setIsMine(Dto.ALL_TRUE);
            Long uts_id=user_time_stamp.insertLine(userTimeStamp);

            if(uts_id!=null){
                //启用线程   3、判断是否陌生人可见，触发时间轴（用户ID，消息ID, 是否陌生人可见）
                JSONArray array;
                if(userDynMsg.getIsStrange()==Dto.ALL_TRUE){
                    //陌生人可见  系统所有用户
                    array= base_users.getAllUsers();
                }else{
                    //只 好友可见
                    array=user_friends.getUserFriendsByUserId(userDynMsg.getUserId());
                }
                //遍历列表为该列表下的用户朋友圈时间轴中添加一条记录
                for (int i = 0; i < array.size(); i++) {
                    JSONObject ob=array.getJSONObject(i);

                }


            }else{
                Dto.writeLog("插入朋友圈时间轴结果："+uts_id);
                return false;
            }

        }else{
            Dto.writeLog("插入动态表结果："+msg_id);
            return false;
        }
        return false;
    }

    @Override
    public JSONObject getMyDynamicMessage(JSONObject in) {

        return null;
    }

    @Override
    public boolean forwardUserDynamicMessage(UserDynMsg userDynMsg) {

        return false;
    }

    @Override
    public boolean deleteUserDynamicMessage(long udmsg_id) {

        return false;
    }

    @Override
    public boolean insertCommentUserDynamicMessage(UserComments userComments) {

        return false;
    }
}
