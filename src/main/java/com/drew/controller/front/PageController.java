package com.drew.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = "/user/pages/casualTrousers")
    public String casualTrousers() {
        return "user/pages/casualTrousers";
    }

    @RequestMapping(value = "/user/pages/coat")
    public String coat() {
        return "user/pages/coat";
    }

    @RequestMapping(value = "/user/pages/downJacket")
    public String downJacket() {
        return "user/pages/downJacket";
    }

    @RequestMapping(value = "/user/pages/jeans")
    public String jeans() {
        return "user/pages/jeans";
    }

    @RequestMapping(value = "/user/pages/knittingSweater")
    public String knittingSweater() {
        return "user/pages/knittingSweater";
    }

    @RequestMapping(value = "/user/pages/sweater")
    public String sweater() {
        return "user/pages/sweater";
    }

    @RequestMapping(value = "login")
    public String home(){
        return "/login";
    }
}
