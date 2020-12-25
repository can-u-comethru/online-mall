package com.drew.controller.front;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.drew.pojo.Goods;
import com.drew.service.CartService;
import com.drew.service.GoodsService;
import com.drew.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsShowController {
    
    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/getAllGoods", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllGoods(){
        List<Goods> goodsList = new ArrayList<Goods>();
        goodsList = goodsService.findAllGoods();
        String allgoods = JSONArray.toJSONString(goodsList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allgoods",allgoods);
        return resultMap;
    }

    @RequestMapping(value = "/searchPre", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchPre(String searchKeyWord, HttpSession httpSession) {
        httpSession.setAttribute("searchKeyWord",searchKeyWord);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/user/search")
    public String search() {
        return "user/search";
    }

    @RequestMapping(value = "/searchGoods", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchGoods(String searchKeyWord){
        System.out.println("我到了SearchProduct"+searchKeyWord);
        Goods goods= new Goods();
        goods = goodsService.findGoodsByName(searchKeyWord);
        String searchResult = JSONArray.toJSONString(goods);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",searchResult);
        System.out.println("我返回了"+searchResult);
        return resultMap;
    }

    @RequestMapping(value = "/getGoodsByID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getGoodsByID(String goodsID) {
        Goods goods = goodsService.findGoodsByID(goodsID);
        String result = JSON.toJSONString(goods);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getGoodsDescription", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getGoodsDescription(String goodsID, HttpSession httpSession) {
        System.out.println("I am here!"+goodsID);
        Goods goods = goodsService.findGoodsByID(goodsID);
        httpSession.setAttribute("productDetail",goods);
        System.out.print("I am here"+goods.getGoodsName());
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }
}
