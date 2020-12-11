package com.drew.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.drew.pojo.Goods;
import com.drew.service.CartService;
import com.drew.service.GoodsService;
import com.drew.service.OrderFormService;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {
    private static String gname="";
    @Resource
    private CartService cartService;
    @Resource
    private OrderFormService orderFormService;
    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/findAllGoods")
    @ResponseBody
    public Map<String,Object>finAllGoods(){
        List<Goods> goodsList=new ArrayList<>();
        goodsList = goodsService.findAllGoods();
        String allgoods = JSONArray.toJSONString(goodsList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allgoods);
        return resultMap;
    }

    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getProductById(String goodsID) {
        Goods goods = goodsService.findGoodsByID(goodsID);
        String result = JSON.toJSONString(goods);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addGoods(String goodsID,String goodsName,String description,String category,float price,int stock,String image) {
        System.out.println("添加了商品："+goodsName);
        gname=goodsName;
        String result ="fail";
        Goods goods = new Goods();
        goods.setGoodsID(goodsID);
        goods.setGoodsName(goodsName);
        goods.setDescription(description);
        goods.setCategory(category );
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setImage(image);
        goodsService.addGoods(goods);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/deleteGoodsByID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProduct(String goodsID) {
        String result ="fail";
        if(goodsService.deleteGoodsByID(goodsID)){
            result="success";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateGoods(String goodsID,String goodsName,String description,String category,float price,int stock,String image) {
        System.out.println("更新了商品："+goodsName);

        String result ="fail";
        Goods goods = new Goods();
        goods.setGoodsID(goodsID);
        goods.setGoodsName(goodsName);
        goods.setDescription(description);
        goods.setCategory(category );
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setImage(image);
        goodsService.addGoods(goods);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
