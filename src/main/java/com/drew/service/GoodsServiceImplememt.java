package com.drew.service;

import com.drew.dao.GoodsDao;
import com.drew.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImplememt extends GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Goods findGoodsByID(String goodsID){
        return super.findGoodsByID(goodsID);
    }
    @Override
    public boolean addGoods(Goods goods){
        return super.addGoods(goods);
    }
    @Override
    public boolean updateGoodsByID(Goods goods){
        return super.updateGoodsByID(goods);
    }

    @Override
    public boolean deleteGoodsByID(String goodsID) {
        return super.deleteGoodsByID(goodsID);
    }
    @Override
    public List<Goods>findAllGoods(){
        return super.findAllGoods();
    }
}
