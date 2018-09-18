package com.smy.biz.global.impl;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */

import com.smy.biz.global.GlobalUserFriendsBiz;
import com.smy.model.UserFriends;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.bean.PageUtil;
import com.zhuoan.ssh.dao.SSHUtilDao;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *@auther:Administrator
 *@Date:2018/9/11
 *@descriotion:
 */
@Service
@Transactional
public class GlobalGlobalUserFriendsBizImpl implements GlobalUserFriendsBiz {
    @Resource
    SSHUtilDao dao;
    @Override
    public JSONArray getUserFriendsByUserId(long user_id) {
        String sql="select rec_user as id from user_friends where user_id=? and id_del=?";
        Object[] par ={user_id, Dto.ALL_FALSE};
        JSONArray array=JSONArray.fromObject(dao.getObjectListBySQL(sql,par,new PageUtil()));
        return array;
    }

    @Override
    public UserFriends getUserFriendsById(long ufd_id) {
        UserFriends ufd= (UserFriends)dao.getObjectById(UserFriends.class,ufd_id);
        return ufd;
    }
}
