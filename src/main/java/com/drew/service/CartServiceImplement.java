package com.drew.service;

import com.drew.dao.CartDao;
import com.drew.pojo.Cart;

import java.util.List;

/**
 * @author pinaster
 */
public class CartServiceImplement extends CartService {
    private CartDao cartDao;

    @Override
    public Cart findCartByID(String cusID) {
        return super.findCartByID(cusID);
    }

    @Override
    public boolean addCart(Cart cart) {
        return super.addCart(cart);
    }

    @Override
    public boolean deleteCartByID(Cart cart) {
        return super.deleteCartByID(cart);
    }

    @Override
    public boolean updateCartByID(Cart cart) {
        return super.updateCartByID(cart);
    }

    @Override
    public List<Cart> findAllCart(String cusID) {
        return super.findAllCart(cusID);
    }
}