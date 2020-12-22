package com.drew.controller.admin;

import com.drew.pojo.Customer;
import com.drew.pojo.OrderForm;
import com.drew.pojo.Orders;
import com.drew.service.CustomerService;
import com.drew.service.GoodsService;
import com.drew.service.OrderFormService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderFormController {
    @Resource
    OrderFormService orderFormService;

    @Resource
    GoodsService goodsService;

    @Resource
    CustomerService customerService;

    @RequestMapping("/orderForm/list")
    public String list(Model model){
        List<OrderForm> orderForms=orderFormService.findAllOrderForm();
        List<Orders> orders=new ArrayList<>();
        for (OrderForm orderForm:orderForms) {
            boolean flag=false;
            for (Orders order:orders) {
                if(order.getOrderFormID().equals(orderForm.getOrderFormID())&&order.getCusID().equals(orderForm.getCusID())){
                    order.setSum(order.getSum()+goodsService.getPriceByID(orderForm.getGoodsID())*orderForm.getAmount());
                    flag=true;
                    break;
                }
            }
            if(flag==false){
                Orders order_t=new Orders(orderForm.getOrderFormID(),orderForm.getCusID(),goodsService.getPriceByID(orderForm.getGoodsID())*orderForm.getAmount(),orderForm.getStatus(),orderForm.getOrderFormDate());
                orders.add(order_t);
            }
        }
        for (Orders order:orders) {
            order.setCusID(customerService.getCusNameByID(order.getCusID()));
        }
        model.addAttribute("orders",orders);
        return "orderForm/list";
    }

    @RequestMapping("/ship")
    public String ship(@PathParam("orderFormID") String orderFormID,Model model){
        List<OrderForm> orderForms=orderFormService.findOrderFormByID(orderFormID);
        for (OrderForm orderForm:orderForms) {
            if(orderForm.getAmount()>goodsService.getStockByID(orderForm.getGoodsID())){
                model.addAttribute("msg","库存不足，请补货后再进行操作！");
                List<OrderForm> orderForms1=orderFormService.findAllOrderForm();
                List<Orders> orders=new ArrayList<>();
                for (OrderForm orderForm1:orderForms1) {
                    boolean flag=false;
                    for (Orders order:orders) {
                        if(order.getOrderFormID().equals(orderForm1.getOrderFormID())&&order.getCusID().equals(orderForm1.getCusID())){
                            order.setSum(order.getSum()+goodsService.getPriceByID(orderForm1.getGoodsID())*orderForm1.getAmount());
                            flag=true;
                            break;
                        }
                    }
                    if(flag==false){
                        Orders order_t=new Orders(orderForm1.getOrderFormID(),orderForm1.getCusID(),goodsService.getPriceByID(orderForm1.getGoodsID())*orderForm1.getAmount(),orderForm1.getStatus(),orderForm1.getOrderFormDate());
                        orders.add(order_t);
                    }
                }
                for (Orders order:orders) {
                    order.setCusID(customerService.getCusNameByID(order.getCusID()));
                }
                model.addAttribute("orders",orders);
                return "orderForm/list";
            }
        }
        for (OrderForm orderForm:orderForms) {
           orderFormService.editOrderFormByID(orderForm.getOrderFormID(),"processed");
           Customer customer=customerService.findCustomerByID(orderForm.getCusID());
           customerService.updateBalanceByID(customer.getCusID(),customer.getBalance()-orderForm.getAmount()*goodsService.getPriceByID(orderForm.getGoodsID()));
           goodsService.updateStockByID(orderForm.getGoodsID(),goodsService.getStockByID(orderForm.getGoodsID())-orderForm.getAmount());
        }
        List<OrderForm> orderForms2=orderFormService.findAllOrderForm();
        List<Orders> orders=new ArrayList<>();
        for (OrderForm orderForm2:orderForms2) {
            boolean flag=false;
            for (Orders order:orders) {
                if(order.getOrderFormID().equals(orderForm2.getOrderFormID())&&order.getCusID().equals(orderForm2.getCusID())){
                    order.setSum(order.getSum()+goodsService.getPriceByID(orderForm2.getGoodsID())*orderForm2.getAmount());
                    flag=true;
                    break;
                }
            }
            if(flag==false){
                Orders order_t=new Orders(orderForm2.getOrderFormID(),orderForm2.getCusID(),goodsService.getPriceByID(orderForm2.getGoodsID())*orderForm2.getAmount(),orderForm2.getStatus(),orderForm2.getOrderFormDate());
                orders.add(order_t);
            }
        }
        for (Orders order:orders) {
            order.setCusID(customerService.getCusNameByID(order.getCusID()));
        }
        model.addAttribute("orders",orders);
        model.addAttribute("msg_","发货成功！");
        return "orderForm/list";
    }

    @RequestMapping("/ignore")
    public String ignore(@PathParam("orderFormID") String orderFormID,Model model){
        List<OrderForm> orderForms=orderFormService.findOrderFormByID(orderFormID);
        for (OrderForm orderForm:orderForms) {
            orderFormService.editOrderFormByID(orderForm.getOrderFormID(),"ignored");
        }
        List<OrderForm> orderForms1=orderFormService.findAllOrderForm();
        List<Orders> orders=new ArrayList<>();
        for (OrderForm orderForm1:orderForms1) {
            boolean flag=false;
            for (Orders order:orders) {
                if(order.getOrderFormID().equals(orderForm1.getOrderFormID())&&order.getCusID().equals(orderForm1.getCusID())){
                    order.setSum(order.getSum()+goodsService.getPriceByID(orderForm1.getGoodsID())*orderForm1.getAmount());
                    flag=true;
                    break;
                }
            }
            if(flag==false){
                Orders order_t=new Orders(orderForm1.getOrderFormID(),orderForm1.getCusID(),goodsService.getPriceByID(orderForm1.getGoodsID())*orderForm1.getAmount(),orderForm1.getStatus(),orderForm1.getOrderFormDate());
                orders.add(order_t);
            }
        }
        for (Orders order:orders) {
            order.setCusID(customerService.getCusNameByID(order.getCusID()));
        }
        model.addAttribute("orders",orders);
        return "orderForm/list";
    }
}
