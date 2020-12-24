package com.drew.controller.admin;

import com.drew.pojo.OrderForm;
import com.drew.pojo.Orders;
import com.drew.service.GoodsService;
import com.drew.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HomepageController {
    @Resource
    OrderFormService orderFormService;

    @Resource
    GoodsService goodsService;

    @RequestMapping("/homepage.html")
    public String enter(Model model){
        int message=0;
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
            if(order.getStatus().equals("pending")){
                message++;
            }
        }
        model.addAttribute("message",message);
        HashMap<String, Float> week = new HashMap<>();
        week.put("星期一", (float) 0);
        week.put("星期二", (float) 0);
        week.put("星期三", (float) 0);
        week.put("星期四", (float) 0);
        week.put("星期五", (float) 0);
        week.put("星期六", (float) 0);
        week.put("星期日", (float) 0);
        for (Orders order:orders) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String today=formatter.format(order.getOrderFormDate());
            if(today.compareTo(getCurrentMonday())>=0&&today.compareTo(getPreviousSunday())<=0){
                SimpleDateFormat  mtwtfss= new SimpleDateFormat("EEEE");
                week.put(mtwtfss.format(order.getOrderFormDate()),week.get(mtwtfss.format(order.getOrderFormDate()))+order.getSum());
            }
            else{
            }
        }
        model.addAttribute("Mon",week.get("星期一"));
        model.addAttribute("Tue",week.get("星期二"));
        model.addAttribute("Wed",week.get("星期三"));
        model.addAttribute("Thu",week.get("星期四"));
        model.addAttribute("Fri",week.get("星期五"));
        model.addAttribute("Sat",week.get("星期六"));
        model.addAttribute("Sun",week.get("星期日"));
        return "homepage";
    }

    // 获得当前日期与本周一相差的天数
    private  int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }
    // 获得当前周- 周一的日期
    private  String getCurrentMonday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        String preMonday=formatter.format(monday);
        return preMonday+" 00:00:00";
    }
    // 获得当前周- 周日  的日期
    private String getPreviousSunday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        String preMonday=formatter.format(monday);
        return preMonday+" 23:59:59";
    }
}
