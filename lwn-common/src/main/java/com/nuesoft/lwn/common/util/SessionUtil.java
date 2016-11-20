package com.nuesoft.lwn.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by yanyong on 2016/11/13.
 */
public class SessionUtil {

    public static HttpSession session(){
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();
        return session;
    }
}
