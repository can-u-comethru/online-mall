package com.drew.controller.admin;

import com.drew.pojo.Customer;
import com.drew.pojo.Evaluation;
import com.drew.pojo.Goods;
import com.drew.service.CustomerService;
import com.drew.service.EvaluationService;
import com.drew.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EvaluationController {
    @Resource
    EvaluationService evaluationService;
    @Resource
    CustomerService customerService;
    @Resource
    GoodsService goodsService;

    @RequestMapping("/evaluation/list")
    public String list(Model model){
        List<Evaluation> evaluations=evaluationService.findAllEvaluation();
        for(Evaluation evaluation:evaluations){
            evaluation.setCusID(customerService.getCusNameByID(evaluation.getCusID()));
            evaluation.setGoodsID(goodsService.getGoodsNameByID(evaluation.getGoodsID()));
        }
        model.addAttribute("evaluations",evaluations);
        return "evaluation/list";
    }
}
