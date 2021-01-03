package com.drew.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        Object userNow=request.getSession().getAttribute("userNow");
        if(userNow==null){
            request.setAttribute("msg","请先登录为会员！");
            request.getRequestDispatcher("/user/login").forward(request,response);
            return false;
        }
        else{
            return true;
        }
    }
}
