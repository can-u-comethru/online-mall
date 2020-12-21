package com.drew.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.drew.pojo.Customer;
import com.drew.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomUserController {
    @Resource
    CustomerService customerService;

    @Resource
    Customer customer;

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/amend_info")
    public String amend_info() {
        return "amend_info";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/control")
    public String control() {
        return "control";
    }

    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public Map<String, Object> customerLogin(String cusID, String cusPwd, HttpSession httpSession) {
        System.out.println("我接收到了登录请求" + cusID + " " + cusPwd);
        String result = "fail";
        Customer customer = customerService.findCustomerByID(cusID);
        if (customer == null) {
            result = "用户不存在！";
        } else {
            Customer customer1 = customerService.findCustomerByID(cusID);
            if (customer1.getCusPwd().equals(cusPwd)) {
                result = "success";
                httpSession.setAttribute("currentUser", customer);
            } else {
                result = "wrong";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public Map<String, Object> doRegister(String cusName, String cusPwd, String cusEmail, String cusTel, String cusAddress) {

        String result = "fail";
        Customer customer = customerService.findCustomerByName(cusName) ;
        if (customer != null) {
            result = "用户名已存在！";
        } else {
            customer = customerService.findCustomerByEmail(cusEmail);
            if (customer != null) {
                result = "emailExist";
            } else {
                Customer customer1 = new Customer();
                customer1.setCusName(cusName);
                System.out.println(cusName);
                customer1.setCusEmail(cusEmail);
                System.out.println(cusEmail);
                customer1.setCusID(customer1.getCusID());
                customer1.setCusAddress(cusAddress);
                customer1.setCusPwd(cusPwd);
                customer1.setCusTel(cusTel);
                customerService.addCustomer(customer1);
                result = "success";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doUpdate")
    @ResponseBody
    public Map<String, Object> doUpdate(Customer customer) {
        String result = "fail";
        customerService.editCustomerByID(customer);
        result = "success";
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(HttpSession httpSession){
        httpSession.setAttribute("currentUser","");
        return "redirect:login";
    }

    @RequestMapping(value = "/getCustomerById")
    @ResponseBody
    public Map<String, Object> getCustomerById(String cusID) {
        Customer customer = customerService.findCustomerByID(cusID);
        String result = JSON.toJSONString(cusID);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
