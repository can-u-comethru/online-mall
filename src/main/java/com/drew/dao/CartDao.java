package com.drew.dao;

import com.drew.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartDao {
    @Select("select * from cart where cusID=#{csuID}")
    List<Cart> findAllCart(String cusID);

    @Select("select * from cart where cusID=#{csuID}")
    Cart findCartByID(@Param("cusID") String cusID);

    @Insert("insert into cart(goodsID,amount) values(#{goodsID},#{amount})")
    boolean addCart(@Param("goodsID") String goodsID,@Param("amount") int amount);

    @Update("update cart set amount=#{amount} where goodsID=#{goodsID}")
    boolean updateCartByID(@Param("goodsID") String goodsID,@Param("amount") int amount);

    @Delete("delete from cart where goodsID=#{goodsID} and cusID=#{csuID}")
    boolean deleteCartByID(@Param("cusID") String cusID,@Param("goodsID") String goodsID);
}
