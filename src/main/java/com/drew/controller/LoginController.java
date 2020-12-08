package com.drew.controller;

import com.drew.pojo.Admin;
import com.drew.service.AdminService;
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

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }

    @RequestMapping("/admin/login")
    public String adminLogin(@RequestParam("adminID") String adminID, @RequestParam("adminPwd") String adminPwd, Model model, HttpSession session){
        List<Admin> admins=adminService.findAllAdmin();
        for (Admin admin:admins) {
            if(admin.getAdminID().equals(adminID)&&admin.getAdminPwd().equals(adminPwd)){
                session.setAttribute("loginAdmin",admin.getAdminName());
                return "redirect:/index.html";
            }
        }
        model.addAttribute("msg","您的管理员ID或者密码有误！");
        return "login";
    }
}