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

    public List<Cart> findAllCart(){
        return cartDao.findAllCart();
    }

    public Cart findCartByID(String goodsID){
        return cartDao.findCartByID(goodsID);
    }

    public boolean isCartExist(String goodsID){
        if(cartDao.findCartByID(goodsID)!=null)
            return true;
        else
            return false;
    }

    public boolean addCart(Cart cart){
        return cartDao.addCart(cart.getGoodsID(),cart.getAmount());
    }

    public boolean deleteCartByID(String goodsID){
        return cartDao.deleteCartByID(goodsID);
    }

    public boolean editCartByID(Cart cart){
        return cartDao.updateCartByID(cart.getGoodsID(),cart.getAmount());
    }
}