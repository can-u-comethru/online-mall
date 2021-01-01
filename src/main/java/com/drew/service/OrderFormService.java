package com.drew.service;

import com.drew.dao.OrderFormDao;
import com.drew.pojo.OrderForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderFormService {
    @Resource
    private OrderFormDao orderFormDao;

    public List<OrderForm> findAllOrderForm(){
        return orderFormDao.findAllOrderForm();
    }

    public List<OrderForm> findOrderFormByID(String orderFormID){
        return orderFormDao.findOrderFormByID(orderFormID);
    }

    public boolean isOrderFormExist(String orderFormID){
        return orderFormDao.findOrderFormByID(orderFormID) != null;
    }

    public boolean addOrderForm(String orderFormID, String cusID, String goodsID, int amount, Date orderFormDate){
        return orderFormDao.addOrderForm(orderFormID, cusID, goodsID, amount, orderFormDate);
    }

    public boolean deleteOrderFormByID(String orderFormID){
        return orderFormDao.deleteOrderFormByID(orderFormID);
    }

    public boolean editOrderFormByID(String orderFormID,String status){
        return orderFormDao.updateOrderFormByID(orderFormID,status);
    }
}
