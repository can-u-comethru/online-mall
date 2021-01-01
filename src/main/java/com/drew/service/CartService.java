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

    public boolean addCart(Cart cart){
        return cartDao.addCart(cart.getCartID(),cart.getCusID(),cart.getGoodsID(),cart.getGoodsName(),cart.getPrice(),cart.getAmount(),cart.getTotal());
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