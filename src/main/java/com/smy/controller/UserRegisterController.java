package com.smy.controller;

import com.smy.biz.useroperate.UserRegisterBiz;
import com.smy.model.BaseUsers;
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
 * @Description: 用户同步
 * @Author: lwt
 * @CreateDate: 2018/9/18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/socialmode")
public class UserRegisterController {

    @Resource
    UserRegisterBiz user_register;
    /**
      * @Description:  实现socialmode向外提供注册用户
      * @Pramers:      传入参数
      * @return:       返回类型
     */
    @RequestMapping("/register.html")
    public void registerUser(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String chain_add=request.getParameter("chain_add");
        String memo=request.getParameter("memo");
        JSONObject obj=new JSONObject();
        if(chain_add!=null&&!chain_add.equals("")){
            BaseUsers baseUsers=new BaseUsers();
            baseUsers.setChanAdd(chain_add);
            baseUsers.setCreateTime(DateUtils.gettimestamp());
            baseUsers.setIdDel(Dto.ALL_FALSE);
            baseUsers.setMemo(memo!=null?memo:"");
            Long effo=user_register.addUserForRegister(baseUsers);
            if(effo!=null){
                obj.put("code", Dto.ALL_TRUE);
                obj.put("msg", "同步成功");
            }else{
                obj.put("code", Dto.ALL_FALSE);
                obj.put("msg", "同步失败");
            }
        }else{
            obj.put("code", Dto.ALL_FALSE);
            obj.put("msg", "用户区块不能为空");
        }
        Dto.printMsg(response,obj.toString());
    }
}
