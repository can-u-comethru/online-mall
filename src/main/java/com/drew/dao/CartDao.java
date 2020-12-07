package com.drew.dao;

import com.drew.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartDao {
    @Select("select * from cart")
    List<Cart> findAllCart();

    @Select("select * from cart where goodsID=#{goodsID}")
    Cart findCartByID(@Param("goodsID") String goodsID);

    @Insert("insert into cart(goodsID,amount) values(#{goodsID},#{amount})")
    boolean addCart(@Param("goodsID") String goodsID,@Param("amount") int amount);

    @Update("update cart set amount=#{amount} where goodsID=#{goodsID}")
    boolean updateCartByID(@Param("goodsID") String goodsID,@Param("amount") int amount);

    @Delete("delete from cart where goodsID=#{goodsID}")
    boolean deleteCartByID(@Param("goodsID") String goodsID);
}
