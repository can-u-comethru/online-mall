package com.drew.controller.admin;

import com.drew.pojo.Admin;
import com.drew.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ManageController {
    @Resource
    AdminService adminService;

    @RequestMapping("/admin/list")
    public String list(Model model){
        List<Admin> admins=adminService.findAllAdmin();
        model.addAttribute("admins",admins);
        return "admin/list";
    }

    @RequestMapping("/admin/add")
    public String add(Admin admin,Model model){
        if(adminService.isAdminExist(admin.getAdminID())==true){
            model.addAttribute("msg","管理员已存在！");
            List<Admin> admins=adminService.findAllAdmin();
            model.addAttribute("admins",admins);
            return "admin/list";
        }
        else{
            adminService.addAdmin(admin);
            return "redirect:/admin/list";
        }
    }

    @RequestMapping("/judge1")
    public String judge1(@PathParam("adminNow_ID") String adminNow_ID,Model model){
        Admin adminNow=adminService.findAdminByID(adminNow_ID);
        if(adminNow.getSupervisor().equals("is")){
            return "admin/add";
        }
        else{
            model.addAttribute("msg","您不是超级管理员！");
            List<Admin> admins=adminService.findAllAdmin();
            model.addAttribute("admins",admins);
            return "admin/list";
        }
    }

    @RequestMapping("/judge2")
    public String judge2(@PathParam("adminID") String adminID, @PathParam("adminNow_ID") String adminNow_ID, Model model){
        Admin adminNow=adminService.findAdminByID(adminNow_ID);
        Admin admin=adminService.findAdminByID(adminID);
        if(adminNow.getSupervisor().equals("is")){
            model.addAttribute("admin",admin);
            return "admin/edit";
        }
        else{
            model.addAttribute("msg","您不是超级管理员！");
            List<Admin> admins=adminService.findAllAdmin();
            model.addAttribute("admins",admins);
            return "admin/list";
        }
    }

    @RequestMapping("/admin/edit")
    public String edit(Admin admin){
        adminService.editAdminByID(admin);
        return "redirect:/admin/list";
    }

    @RequestMapping("/judge3")
    public String judge3(@PathParam("adminID") String adminID, @PathParam("adminNow_ID") String adminNow_ID, Model model){
        Admin adminNow=adminService.findAdminByID(adminNow_ID);
        if(adminNow.getSupervisor().equals("is")){
            if(adminService.findAdminByID(adminID).getSupervisor().equals("is")){
                model.addAttribute("msg","超级管理员不可被删除！");
                List<Admin> admins=adminService.findAllAdmin();
                model.addAttribute("admins",admins);
                return "admin/list";
            }
            else {
                adminService.deleteAdminByID(adminID);
                return "redirect:/admin/list";
            }

        }
        else{
            model.addAttribute("msg","您不是超级管理员！");
            List<Admin> admins=adminService.findAllAdmin();
            model.addAttribute("admins",admins);
            return "admin/list";
        }
    }
}