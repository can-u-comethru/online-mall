package com.drew.service;

import com.drew.pojo.ShoppingRecord;
import com.drew.dao.ShoppingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingRecordServiceImplement implements ShoppingRecordService{

    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Override
    public ShoppingRecord getShoppingRecord(String cusID, String goodsID, String time) {
        return shoppingRecordDao.getShoppingRecord(cusID, goodsID, time);
    }

    @Override
    public void addShoppingRecord(String cusID, String goodsID,String goodsName,String iamge,String time,String status,float price,int amounts) {
        shoppingRecordDao.addShoppingRecord(cusID,goodsID,goodsName,iamge,time,status,price,amounts);
    }

    @Override
    public boolean deleteShoppingRecord(String cusID, String goodsID) {
        return shoppingRecordDao.deleteShoppingRecord(cusID, goodsID);
    }

    @Override
    public boolean updateShoppingRecord(String cusID, String goodsID,String time,String status) {
        return shoppingRecordDao.updateShoppingRecord(cusID, goodsID, time, status);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(String status) {
        return shoppingRecordDao.getShoppingRecordsByOrderStatus(status);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(String cusID) {
        return shoppingRecordDao.getShoppingRecords(cusID);
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        return shoppingRecordDao.getAllShoppingRecords();
    }

    @Override
    public boolean getUserProductRecord(String cusID, String goodsID) {
        return shoppingRecordDao.getUserProductRecord(cusID, goodsID);
    }
}
