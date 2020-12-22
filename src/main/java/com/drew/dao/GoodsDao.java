package com.drew.dao;

import com.drew.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("select * from goods")
    List<Goods> findAllGoods();

    @Select("select * from goods where goodsID=#{goodsID}")
    Goods findGoodsByID(@Param("goodsID") String goodsID);

    @Insert("insert into goods(goodsID,goodsName,description,category,price,stock,image) values(#{goodsID},#{goodsName},#{description},#{category},#{price},#{stock},#{image})")
    boolean addGoods(@Param("goodsID") String goodsID,@Param("goodsName") String goodsName,@Param("description") String description,@Param("category") String category,@Param("price") float price,@Param("stock") int stock,@Param("image") String image);

    @Update("update goods set goodsName=#{goodsName},description=#{description},category=#{category},price=#{price},stock=#{stock},image=#{image} where goodsID=#{goodsID}")
    boolean updateGoodsByID(@Param("goodsID") String goodsID,@Param("goodsName") String goodsName,@Param("description") String description,@Param("category") String category,@Param("price") float price,@Param("stock") int stock,@Param("image") String image);

    @Delete("delete from goods where goodsID=#{goodsID}")
    boolean deleteGoodsByID(@Param("goodsID") String goodsID);

    @Select("select goodsName from goods where goodsID=#{goodsID}")
    String getGoodsNameByID(@Param("goodsID") String goodsID);

    @Select("select price from goods where goodsID=#{goodsID}")
    float getPriceByID(@Param("goodsID") String goodsID);

    @Select("select stock from goods where goodsID=#{goodsID}")
    int getStockByID(@Param("goodsID") String goodsID);

    @Update("update goods set stock=#{stock} where goodsID=#{goodsID}")
    boolean updateStockByID(@Param("goodsID") String goodsID,@Param("stock") int stock);
}
