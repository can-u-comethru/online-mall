package com.drew.service;

import com.drew.dao.CartDao;
import com.drew.pojo.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartService {
    @Resource
    private CartDao cartDao;

    public List<Cart> findAllCart(String cusID){
        return cartDao.findAllCart(cusID);
    }

    public boolean addCart(String cartID,String cusID,String goodsID,String goodsName,float price,int amount,float total){
        return cartDao.addCart(cartID,cusID,goodsID,goodsName,price,amount,total);
    }

    public boolean updateCartByID(String cartID,int amount,float total){
        return cartDao.updateCartByID(cartID,amount,total);
    }

    public boolean updateByID(String cartID){
        return cartDao.updateByID(cartID);
    }

    public boolean deleteCartByID(String cartID){
        return cartDao.deleteCartByID(cartID);
    }

    public Cart findCartByID(String cartID){
        return cartDao.findCartByID(cartID);
    }
}