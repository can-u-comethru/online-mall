package com.drew.controller.front;

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

    @RequestMapping(value = "/cart")
    public String findAllCart() {
        return "cart";
    }

    @RequestMapping(value = "/findCart")
    @ResponseBody
    public Map<String, Object> findCartByID(String cusID) {
        if(cartService.isCartExist(cusID)==true) {
            Cart cart = cartService.findCartByID(cusID);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", cart);
            return resultMap;
        }
        else{
            String result="您的购物车内还没有商品，快去挑选您喜欢商品添加进来吧！";
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", result);
            return resultMap;
        }
    }

    @RequestMapping(value = "/addCart")
    @ResponseBody
    public Map<String, Object> addCart(String cusID, String goodsID, int amount) {
            System.out.println("数量为：" + amount);
            Cart cart = cartService.findCartByID(cusID);
            if (cart == null) {
                Cart cart1 = new Cart();
                cart1.getCusID();
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

    @RequestMapping(value = "/deleteCartByID")
    @ResponseBody
    public Map<String, Object> deleteCartByID(String cusID) {
        cartService.deleteCartByID(cartService.findCartByID(cusID));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        System.out.println("我返回了");
        return resultMap;

    }

    @RequestMapping(value = "/updateCartByID")
    @ResponseBody
    public Map<String,Object> updateCartByID(String cusID){
        cartService.updateCartByID(cartService.findCartByID(cusID));
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("result","success");
        System.out.println("购物车已更新！");
        return resultMap;
    }
}
