package com.drew.dao;

import com.drew.pojo.ShoppingRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingRecordDao {

    @Select("select　* from shoppingrecord where cusID=#{cusID} and goodsID=#{goodsID} and time=#{time}")
    ShoppingRecord getShoppingRecord(@Param("cusID") String cusID,@Param("goodsID")String goodsID, @Param("time") String time);

    @Insert("insert into shoppingrecord(cusID,goodsID,goodsName,image,time,status,price,amounts) values(#{cusID},#{goodsID},#{goodsName},#{iamge},#{time},#{status},#{price},#{amounts})")
    void addShoppingRecord(@Param("cusID")String cusID,@Param("goodsID")String goodsID,@Param("goodsName")String goodsName,@Param("iamge")String iamge,@Param("time")String time,@Param("status")String status,@Param("price")float price,@Param("amounts")int amounts);

    @Delete("delete from shoppingrecord where cusID=#{cusID} and goodsID=#{goodsID}")
    boolean deleteShoppingRecord(@Param("cusID") String cusID,@Param("goodsID") String goodsID);

    @Update("updarte shoppingrecord set status=#{status},time=#{time} where cusID={#cusID} and goodsID=#{goodsID} ")
    boolean updateShoppingRecord(@Param("cusID") String cusID,@Param("goodsID")String goodsID,@Param("status")String status,@Param("time")String time);

    @Select("select * from shoppingrecord where status=#{status}")
    List<ShoppingRecord> getShoppingRecordsByOrderStatus(@Param("status") String status);

    @Select("select * from shoppingrecord where cusID=#{cusID}" )
    List<ShoppingRecord> getShoppingRecords(String cusID);

    @Select("select * from shoppingrecord")
    List<ShoppingRecord> getAllShoppingRecords();

    @Select("select　* from shoppingrecord where cusID=#{cusID} and goodsID=#{goodsID}")
    boolean getUserProductRecord(String cusID,String goodsID);
}
