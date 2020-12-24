package com.drew.controller.admin;

import com.drew.service.AdminService;
import com.drew.service.CustomerService;
import com.drew.service.EvaluationService;
import com.drew.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ReportsController {

    @Resource
    CustomerService customerService;

    @Resource
    AdminService adminService;

    @Resource
    GoodsService goodsService;

    @Resource
    EvaluationService evaluationService;

    @RequestMapping("/reports")
    public String reports(Model model){
        int cnt1=customerService.cnt(),cnt2=adminService.cnt(),cnt3=goodsService.cnt(),cnt4=evaluationService.cnt();
        model.addAttribute("cnt1",cnt1);
        model.addAttribute("cnt2",cnt2);
        model.addAttribute("cnt3",cnt3);
        model.addAttribute("cnt4",cnt4);
        return "reports";
    }
}
