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
    OrderForm findOrderFormByID(@Param("orderFormID") String orderFormID);

    @Insert("insert into orderForm(orderFormID,cusID,sum,status,time) values(#{orderFormID},#{cusID},#{sum},#{status},#{time})")
    boolean addOrderForm(@Param("orderFormID") String orderFormID, @Param("cusID") String cusID, @Param("sum") float sum, @Param("status") String status, @Param("time")Date time);

    @Update("update orderForm set status=#{status} where orderFormID=#{orderFormID}")
    boolean updateOrderFormByID(@Param("orderFormID") String orderFormID,@Param("status") String status);

    @Delete("delete from orderForm where orderFormID=#{orderFormID}")
    boolean deleteOrderFormByID(@Param("orderFormID") String orderFormID);
}
