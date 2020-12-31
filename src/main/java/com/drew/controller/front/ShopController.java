package com.drew.controller.front;

import com.drew.pojo.Customer;
import com.drew.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ShopController {
    @Resource
    CustomerService customerService;

    @RequestMapping("/user/shop")
    public String shop(){
        return "user/shop";
    }

    @RequestMapping("/user/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/user/glasses")
    public String glasses(){
        return "user/glasses";
    }

    @RequestMapping("/user/contact")
    public String contact(){
        return "user/contact";
    }

    @RequestMapping("/user/about")
    public String about(){
        return "user/about";
    }

    @RequestMapping("/user/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping("/user/register")
    public String register(){
        return "user/register";
    }

    @RequestMapping("/user/login_")
    public String login_(@Param("cusID") String cusID, @Param("cusPwd") String cusPwd, Model model){
        List<Customer> customers=customerService.findAllCustomer();
        for(Customer customer:customers){
            if(customer.getCusID().equals(cusID)&&customer.getCusPwd().equals(cusPwd)){
                return "/user/index";
            }
        }
        model.addAttribute("msg","输入的账户或密码有误，请重试！");
        return "/user/login";
    }

    @RequestMapping("/user/register_")
    public String register_(Customer customer,Model model){
        if(customerService.isCustomerExist(customer.getCusID())==false){
            customerService.addCustomer(customer);
            model.addAttribute("msg_","注册成功,现在可以登录为会员了！");
            return "/user/login";
        }
        else {
            model.addAttribute("msg","注册失败，用户已存在！");
            return "/user/register";
        }
    }
}
