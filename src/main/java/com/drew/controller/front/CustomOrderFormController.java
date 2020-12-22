package com.drew.controller.front;

import com.drew.pojo.OrderForm;
import com.drew.service.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomOrderFormController {

    @Resource
    OrderFormService orderFormService;

    @RequestMapping(value = "/OrderForm")
    public String findAllOrderForm(){return "OrderForm";}

    @RequestMapping(value = "/findOrderFormByID")
    @ResponseBody
    public Map<String,Object> findOrderFormByID(String oderFormID){
        if(!orderFormService.isOrderFormExist(oderFormID)){
            String result="您还没有订单，欢迎您在本商城购物！";
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", result);
            return resultMap;
        }
        else{
            OrderForm order=orderFormService.findOrderFormByID(oderFormID);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result",order);
            return resultMap;
        }
    }

    @RequestMapping(value = "/addOrderForm")
    @ResponseBody
    public Map<String,Object> addOrderForm(OrderForm orderForm){
            System.out.println("您的订单生成中，请稍候...");
            orderForm.setOrderFormID(orderForm.getOrderFormID());
            orderForm.setCusID(orderForm.getCusID());
            orderForm.setStatus(orderForm.getStatus());
            orderForm.setAmount(orderForm.getAmount());
            Date date=new Date();
            orderForm.setOrderFormDate(date);
            orderFormService.addOrderForm(orderForm);
            String result="订单生成成功！";
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result",result);
            return resultMap;
    }

    @RequestMapping(value = "/deleteOrderFormByID")
    @ResponseBody
    public Map<String, Object> deleteOrderFormByID(String orderFormID) {
        orderFormService.deleteOrderFormByID(orderFormID);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        System.out.println("订单成功删除！");
        return resultMap;
    }
}
