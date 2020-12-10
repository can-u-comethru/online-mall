package com.drew.controller;

import com.drew.dao.CartDao;
import com.drew.pojo.Cart;
import com.drew.service.CartService;
import com.drew.service.GoodsService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pinaster
 */
@Controller
public class CartController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private CartService cartService;

    @RequestMapping(value = "/Cart")
    public String Cart() {
        return "Cart";
    }

    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCart(String cusID, String goodsID, int amount) {
        System.out.println("数量为：" + amount);
        Cart cart = cartService.findCartByID(cusID);
        if (cart == null) {
            Cart cart1 = new Cart();
            cart1.getcusID();
            cart1.getGoodsID();
            cart1.getAmount();
            cart1.setPrice(goodsService.findGoodsByID(goodsID).getPrice() * amount);
            cartService.addCart(cart1);
        } else {
            cart.setAmount(cart.getAmount() + amount);
            cart.setPrice(goodsService.findGoodsByID(goodsID).getPrice() * cart.getAmount());
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        System.out.println("返回");
        return resultMap;
    }

    @RequestMapping(value = "/findCart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCartByID(String cusID) {
       Cart cart = cartService.findCartByID(cusID);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", cart);
        return resultMap;
    }

    @RequestMapping(value = "/deleteCartByID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCartByID(String cusID) {
        cartService.deleteCartByID(cartService.findCartByID(cusID));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        System.out.println("我返回了");
        return resultMap;

    }
}
