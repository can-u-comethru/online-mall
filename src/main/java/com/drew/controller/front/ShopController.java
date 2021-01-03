package com.drew.controller.front;

import com.drew.pojo.Cart;
import com.drew.pojo.Customer;
import com.drew.pojo.Goods;
import com.drew.pojo.OrderForm;
import com.drew.service.CartService;
import com.drew.service.CustomerService;
import com.drew.service.GoodsService;
import com.drew.service.OrderFormService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class ShopController {
    @Resource
    CustomerService customerService;

    @Resource
    CartService cartService;

    @Resource
    OrderFormService orderFormService;

    @Resource
    GoodsService goodsService;

    @RequestMapping("/user/shop")
    public String shop(){
        return "user/shop";
    }

    @RequestMapping("/user/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/user/glasses")
    public String glasses(Model model){
        List<Goods> items=goodsService.findAllGoods();
        model.addAttribute("items",items);
        return "user/glasses";
    }

    @RequestMapping("/user/cart")
    public String contact(Model model,HttpSession session){
        String cusName=(String)session.getAttribute("userNow");
        String cusID=customerService.getCusIDByName(cusName);
        List<Cart> carts=cartService.findAllCart(cusID);
        float total_=0;
        for (Cart cart:carts) {
            total_+=cart.getTotal();
        }
        model.addAttribute("carts",carts);
        model.addAttribute("total_",total_);
        return "user/cart";
    }

    @RequestMapping("/user/about")
    public String about(){
        return "user/about";
    }

    @RequestMapping("/user/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping("/user/register")
    public String register(){
        return "user/register";
    }

    @RequestMapping("/user/login_")
    public String login_(@Param("cusID") String cusID, @Param("cusPwd") String cusPwd, Model model, HttpSession session){
        List<Customer> customers=customerService.findAllCustomer();
        for(Customer customer:customers){
            if(customer.getCusID().equals(cusID)&&customer.getCusPwd().equals(cusPwd)){
                session.setAttribute("userNow",customerService.getCusNameByID(cusID));
                return "redirect:/user/index.html";
            }
        }
        model.addAttribute("msg","输入的账户或密码有误，请重试！");
        return "/user/login";
    }

    @RequestMapping("/user/register_")
    public String register_(Customer customer,Model model){
        if(customerService.isCustomerExist(customer.getCusID())==false){
            customerService.addCustomer(customer);
            model.addAttribute("msg_","注册成功,现在可以登录为会员了！");
            return "/user/login";
        }
        else {
            model.addAttribute("msg","注册失败，用户已存在！");
            return "/user/register";
        }
    }

    @RequestMapping("/user/inc")
    public String inc(@PathParam("cartID") String cartID){
        Cart cart=cartService.findCartByID(cartID);
        if(cart.getAmount()+1<=goodsService.getStockByID(cart.getGoodsID())){
            cartService.updateCartByID(cartID,cart.getAmount()+1,cart.getTotal()+cart.getPrice());
            return "redirect:/user/cart";
        }
        else {
            return "redirect:/user/cart";
        }
    }

    @RequestMapping("/user/dec")
    public String dec(@PathParam("cartID") String cartID){
        Cart cart=cartService.findCartByID(cartID);
        if(cart.getAmount()-1>=1){
            cartService.updateCartByID(cartID,cart.getAmount()-1,cart.getTotal()-cart.getPrice());
            return "redirect:/user/cart";
        }
        else {
            return "redirect:/user/cart";
        }
    }

    @RequestMapping("/user/delete")
    public String delete(@PathParam("cartID") String cartID){
        cartService.deleteCartByID(cartID);
        return "redirect:/user/cart";
    }

    @RequestMapping("/user/submit")
    public String submit(@PathParam("cusName") String cusName,@PathParam("total") float total,Model model,HttpSession session){
        String cusID=customerService.getCusIDByName(cusName);
        if(total<=customerService.getBalanceByID(cusID)){
            Random random=new Random();
            int num=random.nextInt(999998999)+1000;
            String ID=Integer.toString(num);
            Date date=new Date();
            List<Cart> carts=cartService.findAllCart(cusID);
            for (Cart cart:carts) {
                orderFormService.addOrderForm(ID,cusID,cart.getGoodsID(),cart.getAmount(),date);
                cartService.updateByID(cart.getCartID());
            }
            customerService.updateBalanceByID(cusID,customerService.getBalanceByID(cusID)-total);
            model.addAttribute("orderFormID",ID);
            return "successfully";
        }
        else{
            model.addAttribute("msg","账户余额不足，请先充值！");
            String cusName1=(String)session.getAttribute("userNow");
            String cusID1=customerService.getCusIDByName(cusName1);
            List<Cart> carts=cartService.findAllCart(cusID1);
            float total_=0;
            for (Cart cart:carts) {
                total_+=cart.getTotal();
            }
            model.addAttribute("carts",carts);
            model.addAttribute("total_",total_);
            return "/user/cart";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/user/index";
    }

    @RequestMapping("/user/add")
    public String add(@PathParam("goodsID") String goodsID,HttpSession session){
        Goods goods=goodsService.findGoodsByID(goodsID);
        Random random=new Random();
        int num=random.nextInt(999998999)+1000;
        String ID=Integer.toString(num);
        String cusID=customerService.getCusIDByName((String)session.getAttribute("userNow"));
        cartService.addCart(ID,cusID,goodsID,goods.getGoodsName(),goods.getPrice(),1,goods.getPrice());
        return "/purchase";
    }

    @RequestMapping("/user/verify")
    public String verify(){
        return "/user/verify";
    }

    @RequestMapping("/user/verify_")
    public String verify_(HttpSession session,String cusPwd,Model model){
        Customer customer=customerService.findCustomerByID(customerService.getCusIDByName((String)session.getAttribute("userNow")));
        if(customer.getCusPwd().equals(cusPwd)){
            model.addAttribute("msg","verified");
            return "/user/verify";
        }
        else{
            model.addAttribute("msg","failed");
            return "/user/verify";
        }
    }

    @RequestMapping("/user/edit")
    public String edit(HttpSession session,Model model){
        model.addAttribute("customer",customerService.findCustomerByID(customerService.getCusIDByName((String)session.getAttribute("userNow"))));
        return "/user/edit";
    }

    @RequestMapping("/user/recharge")
    public String recharge(HttpSession session,Model model){
        model.addAttribute("customer",customerService.findCustomerByID(customerService.getCusIDByName((String)session.getAttribute("userNow"))));
        return "/user/recharge";
    }

    @RequestMapping("/user/edit_")
    public String edit_(Customer customer,Model model,HttpSession session){
        customerService.editCustomerByID(customer);
        session.invalidate();
        model.addAttribute("msg_","成功修改信息，请重新登录！");
        return "/user/login";
    }

    @RequestMapping("/user/recharge_")
    public String recharge_(HttpSession session,float balance){
        Customer customer=customerService.findCustomerByID(customerService.getCusIDByName((String)session.getAttribute("userNow")));
        customerService.updateBalanceByID(customer.getCusID(),customer.getBalance()+balance);
        return "thanks";
    }

    @RequestMapping("thanks")
    public String thanks(){
        return "thanks";
    }

    @RequestMapping("/user/orders")
    public String orders(){
        return "/user/orders";
    }

    @RequestMapping("/user/orders_")
    public String orders_(String orderFormID,Model model,HttpSession session){
        String cusID=customerService.getCusIDByName((String)session.getAttribute("userNow"));
        if(orderFormService.isOrderFormExist(orderFormID,cusID)){
            List<OrderForm> orderForms=orderFormService.findOrdersByID(orderFormID,cusID);
            model.addAttribute("orderForms",orderForms);
            return "/user/orderList";
        }
        else {
            model.addAttribute("msg","订单不存在！");
            return "/user/orders";
        }
    }
}
