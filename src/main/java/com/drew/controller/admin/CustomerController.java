package com.drew.controller.admin;

import com.drew.pojo.Customer;
import com.drew.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class CustomerController {
    @Resource
    CustomerService customerService;

    @RequestMapping("/customer/list")
    public String list(Model model){
        List<Customer> customers=customerService.findAllCustomer();
        model.addAttribute("customers",customers);
        return "customer/list";
    }

    @RequestMapping("/customer/delete")
    public String delete(@PathParam("cusID") String cusID){
        customerService.deleteCustomerByID(cusID);
        return "redirect:/customer/list";
    }
}
