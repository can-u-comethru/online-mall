package com.drew.service;

import com.drew.pojo.ShoppingRecord;

import java.util.List;

public interface ShoppingRecordService {
    ShoppingRecord getShoppingRecord(String cusID, String goodsID,String time);

    void addShoppingRecord(String cusID, String goodsID,String goodsName,String iamge,String time,String status,float price,int amounts);

    boolean deleteShoppingRecord(String cusID,String goodsID);

    boolean updateShoppingRecord(String cusID, String goodsID,String time,String status);

    List<ShoppingRecord> getShoppingRecordsByOrderStatus(String status);

    List<ShoppingRecord> getShoppingRecords(String cusID);

    List<ShoppingRecord> getAllShoppingRecords();

    boolean getUserProductRecord(String cusID,String goodsID);
}
