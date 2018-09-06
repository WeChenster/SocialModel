package com.zhuoan.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookies操作工具类
 * @author nanoha
 *
 */
public class CookiesUtil {
	/**
     * 添加Cookie
     * @return
     * @throws Exception
     */
    public static void addCookie(String name,String value,HttpServletResponse res){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);
        res.addCookie(cookie);
        
        Cookie cookie2 = new Cookie(name, value);
        cookie2.setPath("/nanny");
        cookie2.setMaxAge(60 * 60 * 24 * 7);
        res.addCookie(cookie2);
    }
    /**
     * 获得Cookie
     * @return
     * @throws Exception
     */
    public static String getCookie(String name,HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        String value=null;
        if(cookies==null)
        	return value;
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(name))
            {
            	value=cookie.getValue();
            	break;
            }
        }
        return value;
    }
}
