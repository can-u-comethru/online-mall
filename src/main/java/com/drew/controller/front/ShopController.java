package com.drew.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {
    @RequestMapping("/user/shop")
    public String shop(){
        return "user/shop";
    }

    @RequestMapping("/user/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/user/glasses")
    public String glasses(){
        return "user/glasses";
    }

    @RequestMapping("/user/contact")
    public String contact(){
        return "user/contact";
    }

    @RequestMapping("/user/about")
    public String about(){
        return "user/about";
    }
}
