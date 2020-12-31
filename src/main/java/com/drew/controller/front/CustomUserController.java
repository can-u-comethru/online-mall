//package com.drew.controller.front;
//
//import com.alibaba.fastjson.JSON;
//import com.drew.pojo.Customer;
//import com.drew.service.CustomerService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.util.*;
//
//@Controller
//public class CustomUserController {
//    @Resource
//    CustomerService customerService;
//
//    @RequestMapping(value = "/user/register")
//    public String register() {
//        return "user/register";
//    }
//
//    @RequestMapping(value = "/user/amend_info")
//    public String amend_info() {
//        return "user/amend_info";
//    }
//
//    @RequestMapping(value = "/user/login")
//    public String login() {
//        return "user/login";
//    }
//
//    @RequestMapping(value = "/user/main.html")
//    public String main() {
//        return "user/main";
//    }
//
//    @RequestMapping(value = "/user/doLogin", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> customerLogin(String cusID, String cusPwd, HttpSession httpSession) {
//        System.out.println("我接收到了登录请求" + cusID + " " + cusPwd);
//        String result = "fail";
//        Customer customer = customerService.findCustomerByID(cusID);
//        if (customer == null) {
//            result = "unexist";
//        } else {
//            Customer customer1 = customerService.findCustomerByID(cusID);
//            if (customer1.getCusPwd().equals(cusPwd)) {
//                result = "success";
//                httpSession.setAttribute("currentUser", customer);
//            } else {
//                result = "wrong";
//            }
//        }
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("result", result);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "/user/doRegister", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> doRegister(String cusName, String cusPwd, String cusEmail, String cusTel, String cusAddress) {
//
//        String result = "fail";
//        Customer customer = customerService.findCustomerByName(cusName) ;
//        if (customer != null) {
//            result = "用户名已存在！";
//        } else {
//            customer = customerService.findCustomerByEmail(cusEmail);
//            if (customer != null) {
//                result = "emailExist";
//            } else {
//                Customer customer1 = new Customer();
//                customer1.setCusName(cusName);
//                System.out.println(cusName);
//                customer1.setCusEmail(cusEmail);
//                System.out.println(cusEmail);
//                customer1.setCusID(customer1.getCusID());
//                customer1.setCusAddress(cusAddress);
//                customer1.setCusPwd(cusPwd);
//                customer1.setCusTel(cusTel);
//                customer1.setBalance(0.0f);
//                customerService.addCustomer(customer1);
//                result = "success";
//            }
//        }
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("result", result);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "/user/doUpdate", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> doUpdate(String cusName, String cusPwd, String cusEmail, String cusTel, String cusAddress) {
//        String result;
//        Customer customer1=customerService.findCustomerByName(cusName);
//        if(customer1==null){
//            result = "fail";
//        }
//        else {
//            customer1.setCusEmail(cusEmail);
//            customer1.setCusAddress(cusAddress);
//            customer1.setCusPwd(cusPwd);
//            customer1.setCusTel(cusTel);
//            customerService.editCustomerByID(customer1);
//            result = "success";
//        }
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("result", result);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "/user/doLogout")
//    public String doLogout(HttpSession httpSession){
//        httpSession.setAttribute("currentUser","");
//        return "redirect: /user/login.html";
//    }
//
//    @RequestMapping(value = "/getCustomerById")
//    @ResponseBody
//    public Map<String, Object> getCustomerById(String cusID) {
//        Customer customer = customerService.findCustomerByID(cusID);
//        String result = JSON.toJSONString(customer);
//        Map<String,Object> resultMap = new HashMap<String,Object>();
//        resultMap.put("result",result);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "/getUserAddressAndPhoneNumber", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> getUserAddressAndPhoneNumber(String cusID) {
//        String address = customerService.findCustomerByID(cusID).getCusAddress();
//        String phoneNumber = customerService.findCustomerByID(cusID).getCusTel();
//        Map<String,Object> resultMap = new HashMap<String,Object>();
//        resultMap.put("address",address);
//        resultMap.put("phoneNumber",phoneNumber);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "/chargeBalance", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> updateBalance(String cusID,float Balance){
//        String result;
//        if(Balance>0) {
//            Customer customer = customerService.findCustomerByID(cusID);
//            String id = customer.getCusID();
//            Float overage = customer.getBalance();
//            Float sum = overage + Balance;
//            customerService.updateBalanceByID(id, sum);
//            result="success";
//        }
//        else{
//            result="failed";
//        }
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("result", result);
//        return resultMap;
//    }
//}
