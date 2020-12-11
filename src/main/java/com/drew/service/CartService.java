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

    public Cart findCartByID(String cusID){
        return cartDao.findCartByID(cusID);
    }

    public boolean isCartExist(String cusID){
        if(cartDao.findCartByID(cusID)!=null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addCart(Cart cart){
        return cartDao.addCart(cart.getGoodsID(),cart.getAmount());
    }

    public boolean deleteCartByID(Cart cart){
        return cartDao.deleteCartByID(cart.getcusID(),cart.getGoodsID());
    }

    public boolean updateCartByID(Cart cart){
        return cartDao.updateCartByID(cart.getGoodsID(),cart.getAmount());
    }
}