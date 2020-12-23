package com.drew.service;

import com.drew.dao.GoodsDao;
import com.drew.pojo.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;

    public List<Goods> findAllGoods(){
        return goodsDao.findAllGoods();
    }

    public Goods findGoodsByID(String goodsID){
        return goodsDao.findGoodsByID(goodsID);
    }

    public boolean isGoodsExist(String goodsID){
        if(goodsDao.findGoodsByID(goodsID)!=null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addGoods(Goods goods){
        return goodsDao.addGoods(goods.getGoodsID(),goods.getGoodsName(),goods.getDescription(),goods.getCategory(),goods.getPrice(),goods.getStock(),goods.getImage());
    }
    public boolean updateGoodsByID(Goods goods){
        return goodsDao.updateGoodsByID(goods.getGoodsID(),goods.getGoodsName(),goods.getDescription(),goods.getCategory(),goods.getPrice(),goods.getStock(),goods.getImage());
    }

    public boolean deleteGoodsByID(String goodsID){
        return goodsDao.deleteGoodsByID(goodsID);
    }

    public boolean editGoodsByID(Goods goods){
        return goodsDao.updateGoodsByID(goods.getGoodsID(),goods.getGoodsName(),goods.getDescription(),goods.getCategory(),goods.getPrice(),goods.getStock(),goods.getImage());
    }

    public String getGoodsNameByID(String goodsID){
        return goodsDao.getGoodsNameByID(goodsID);
    }

    public float getPriceByID(String goodsID){
        return goodsDao.getPriceByID(goodsID);
    }

    public int getStockByID(String goodsID){
        return goodsDao.getStockByID(goodsID);
    }

    public boolean updateStockByID(String goodsID,int stock){
        return goodsDao.updateStockByID(goodsID,stock);
    }
}
