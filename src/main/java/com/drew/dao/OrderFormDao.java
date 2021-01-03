package com.drew.dao;

import com.drew.pojo.OrderForm;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderFormDao {
    @Select("select * from orderForm")
    List<OrderForm> findAllOrderForm();

    @Select("select * from orderForm where orderFormID=#{orderFormID}")
    List<OrderForm> findOrderFormByID(@Param("orderFormID") String orderFormID);

    @Select("select * from orderForm where orderFormID=#{orderFormID} and cusID=#{cusID}")
    List<OrderForm> findOrdersByID(@Param("orderFormID") String orderFormID,@Param("cusID") String cusID);

    @Insert("insert into orderForm(orderFormID,cusID,goodsID,amount,status,orderFormDate) values(#{orderFormID},#{cusID},#{goodsID},#{amount},'pending',#{orderFormDate})")
    boolean addOrderForm(@Param("orderFormID") String orderFormID,@Param("cusID") String cusID,@Param("goodsID") String goodsID,@Param("amount") int amount,@Param("orderFormDate") Date orderFormDate);

    @Update("update orderForm set status=#{status} where orderFormID=#{orderFormID}")
    boolean updateOrderFormByID(@Param("orderFormID") String orderFormID,@Param("status") String status);

    @Delete("delete from orderForm where orderFormID=#{orderFormID}")
    boolean deleteOrderFormByID(@Param("orderFormID") String orderFormID);
}
