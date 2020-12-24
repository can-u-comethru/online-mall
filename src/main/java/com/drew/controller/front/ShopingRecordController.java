package com.drew.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.drew.pojo.Goods;
import com.drew.pojo.ShoppingRecord;
import com.drew.service.GoodsService;
import com.drew.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopingRecordController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private ShoppingRecordService shoppingRecordService;

    @RequestMapping(value = "/shopping_record")
    public String shopping_record(){
        return "shopping_record";
    }

    @RequestMapping(value = "/shopping_handle")
    public String shopping_handle(){
        return "shopping_handle";
    }

    @RequestMapping(value = "/addShoppingRecord")
    @ResponseBody
    public Map<String,Object> addShoppingRecord(String cusID, String goodsID,String goodsName,String iamge,String time,String status,float price,int amounts){
        System.out.println("我添加了"+cusID+" "+goodsID);
        String result;
        Goods goods = goodsService.findGoodsByID(cusID);
        if(amounts<=goods.getStock()) {
            ShoppingRecord shoppingRecord = new ShoppingRecord();
            shoppingRecord.setCusID(cusID);
            shoppingRecord.setGoodsID(goodsID);
            shoppingRecord.setPrice(goods.getPrice() * amounts);
            shoppingRecord.setAmounts(amounts);
            shoppingRecord.setStatus("0");
            Date date = new Date();
            shoppingRecord.setTime(date);
            goods.setStock(goods.getStock()-amounts);
            goodsService.updateGoodsByID(goods);
            shoppingRecordService.addShoppingRecord(cusID, goodsID, goodsName, iamge, time, status, price, amounts);
            result = "success";
        }
        else{
            result = "unEnough";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/changeShoppingRecord")
    @ResponseBody
    public Map<String,Object> changeShoppingRecord(String cusID,String goodsID,String time,String status){
        System.out.println("我接收了"+cusID+" "+goodsID+" "+time+" "+status);
        ShoppingRecord shoppingRecord = shoppingRecordService.getShoppingRecord(cusID,goodsID,time);
        System.out.println("我获取到了了"+shoppingRecord.getTime());
        shoppingRecord.setStatus(status);
        shoppingRecordService.updateShoppingRecord(cusID, goodsID, time, status);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        System.out.println("我成功返回了");
        return resultMap;
    }

    @RequestMapping(value = "/getShoppingRecords")
    @ResponseBody
    public Map<String,Object> getShoppingRecords(String cusID){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecords(cusID);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }

    @RequestMapping(value = "/getShoppingRecordsByOrderStatus")
    @ResponseBody
    public Map<String,Object> getShoppingRecordsByOrderStatus(String status){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecordsByOrderStatus(status);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }

    @RequestMapping(value = "/getAllShoppingRecords")
    @ResponseBody
    public Map<String,Object> getAllShoppingRecords(){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getAllShoppingRecords();
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }

    @RequestMapping(value = "/getUserProductRecord")
    @ResponseBody
    public Map<String,Object> getUserProductRecord(String cusID,String goodsID){
        String result = "false";
        if(shoppingRecordService.getUserProductRecord(cusID,goodsID)){
            result = "true";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
