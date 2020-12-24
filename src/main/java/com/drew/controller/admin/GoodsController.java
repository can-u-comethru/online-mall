package com.drew.controller.admin;

import com.drew.pojo.Goods;
import com.drew.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class  GoodsController {
    @Resource
    GoodsService goodsService;

    @RequestMapping("/goods/list")
    public String list(Model model){
        List<Goods> items=goodsService.findAllGoods();
        model.addAttribute("items",items);
        return "goods/list";
    }

    @RequestMapping("/transit1")
    public String transit1(@PathParam("goodsID") String goodsID,Model model){
        Goods goods=goodsService.findGoodsByID(goodsID);
        model.addAttribute("goods",goods);
        return "goods/edit";
    }

    @RequestMapping("/goods/edit")
    public String edit(Goods goods){
        goodsService.editGoodsByID(goods);
        return "redirect:/goods/list";
    }

    @RequestMapping("/transit2")
    public String transit2(){
        return "goods/add";
    }

    @RequestMapping("/goods/add")
    public String add(Goods goods,Model model){
        if(goodsService.isGoodsExist(goods.getGoodsID())==true){
            model.addAttribute("msg","该商品已存在，请勿重复添加！");
            List<Goods> items=goodsService.findAllGoods();
            model.addAttribute("items",items);
            return "goods/list";
        }
        goodsService.addGoods(goods);
        return "redirect:/goods/list";
    }

    @RequestMapping("/goods/delete")
    public String transit3(@PathParam("goodsID") String goodsID){
        goodsService.deleteGoodsByID(goodsID);
        return "redirect:/goods/list";
    }
}
