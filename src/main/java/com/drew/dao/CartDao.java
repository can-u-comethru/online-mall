package com.drew.dao;

import com.drew.pojo.Cart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface CartDao {
    @Select("select * from cart where cusID=#{cusID} and statement='pending'")
    List<Cart> findAllCart(@Param("cusID") String cusID);

    @Insert("insert into cart(cartID,cusID,goodsID,goodsName,price,amount,total,statement) values(#{cartID},#{cusID},#{goodsID},#{goodsName},#{price},#{amount},#{total},'pending')")
    boolean addCart(@Param("cartID") String cartID,@Param("cusID") String cusID,@Param("goodsID") String goodsID,@Param("goodsName") String goodsName,@Param("price") float price,@Param("amount") int amount,@Param("total") float total);

    @Update("update cart set amount=#{amount},total=#{total} where cartID=#{cartID}")
    boolean updateCartByID(@Param("cartID") String cartID,@Param("amount") int amount,@Param("total") float total);

    @Update("update cart set statement='processed' where cartID=#{cartID}")
    boolean updateByID(@Param("cartID") String cartID);

    @Delete("delete from cart where cartID=#{cartID}")
    boolean deleteCartByID(@Param("cartID") String cartID);

    @Select("select * from cart where cartID=#{cartID}")
    Cart findCartByID(@Param("cartID") String cartID);
}
