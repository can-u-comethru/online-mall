package com.drew.controller.admin;

import com.drew.pojo.Admin;
import com.drew.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Resource
    AdminService adminService;

    @RequestMapping("/admin/login")
    public String login(@Param("adminID") String adminID,@Param("adminPwd") String adminPwd,Model model,HttpSession session){
        List<Admin> admins=adminService.findAllAdmin();
        for (Admin admin:admins) {
            if(admin.getAdminID().equals(adminID)&&admin.getAdminPwd().equals(adminPwd)){
                session.setAttribute("adminNow_N",admin.getAdminName());
                session.setAttribute("adminNow_ID",admin.getAdminID());
                return "redirect:/main.html";
            }
        }
        model.addAttribute("msg","管理员ID或者密码有误！");
        return "login";
    }

    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}