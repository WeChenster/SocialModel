package com.smy.biz.global.impl;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */

import com.smy.biz.global.GlobalUserFriendsBiz;
import com.zhuoan.dto.Dto;
import com.zhuoan.ssh.dao.SSHUtilDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    public JSONObject getUserFriendsByUserId(long user_id, int now_page) {
        int size=50;
        JSONObject obj=new JSONObject();
        String sql="select $ from user_friends where user_id=? and id_del=?";
        String countsql=sql.replace("$","count(id)");
        String listsql=sql.replace("$","id,rec_user,friend_memo");
        Object[] par ={user_id, Dto.ALL_FALSE};
        int total=dao.getCount(countsql,par);
        JSONArray array=JSONArray.fromObject(dao.getObjectListBySQL(listsql,par,now_page,size));
        //TODO：遍历数组将rec_user 变成昵称头像

        obj.put("total",total);
        obj.put("size",size);
        obj.put("page",now_page);
        obj.put("array",array);

        return obj;
    }

    @Override
    public JSONObject getUserFriendsById(long ufd_id) {
        String sql="select id,create_time,friend_memo,top,msg_not,backg,hide_me,hide_her,is_blacklist from user_friends where id=? and id_del=?";
        Object[] par ={ufd_id,Dto.ALL_FALSE};
        JSONObject obj=JSONObject.fromObject(dao.getObjectBySQL(sql,par));
        return obj;
    }
}
