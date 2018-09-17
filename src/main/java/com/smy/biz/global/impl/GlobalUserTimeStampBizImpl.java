package com.smy.biz.global.impl;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/11
 * @Version: 1.0
 */

import com.smy.biz.global.GlobalUserTimeStampBiz;
import com.smy.model.UserTimeStamp;
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
public class GlobalUserTimeStampBizImpl implements GlobalUserTimeStampBiz {
    @Resource
    SSHUtilDao dao;

    @Override
    public Long insertLine(UserTimeStamp userTimeStamp) {
        Long id= (Long)dao.saveObject(userTimeStamp);
        return id;
    }

    @Override
    public JSONObject getUserTimeStampListByUserId(long user_id, int size, int now_page) {
        JSONObject obj=new JSONObject();
        String sql="select $ from user_time_stamp where user_id=? order by id desc";
        String countsql=sql.replace("$","count(id)");
        String listsql=sql.replace("$"," id,user_id,udmsg_id,is_mine,create_time ");
        Object[] par={user_id};
        int count =dao.getCount(countsql,par);
        JSONArray array=JSONArray.fromObject(dao.getObjectListBySQL(listsql,par,now_page,size));

        obj.put("total",count);
        obj.put("size",size);
        obj.put("page",now_page);
        obj.put("array",array);
        return obj;
    }

    @Override
    public boolean deleteByMsgId(long udmsg_id) {
        String sql ="delete from user_time_stamp where udmsg_id=?";
        Object[] par={udmsg_id};
        return dao.delObjectBySQL(sql,par);
    }


}
