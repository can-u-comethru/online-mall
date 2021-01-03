package com.drew.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/homepage.html").setViewName("homepage");
        registry.addViewController("/user/index.html").setViewName("/user/index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html",
                "/admin/login","/user/**",
                "/css/*","/js/**","/img/**","/fonts/**");
        registry.addInterceptor(new UserHandlerInterceptor()).addPathPatterns("/user/**").excludePathPatterns("/user/login",
                "/user/register","/user/index","/user/shop","/user/about","/user/glasses",
                "/user/login_","/user/register_",
                "/css/*","/js/**","/img/**","/fonts/**");
    }
}