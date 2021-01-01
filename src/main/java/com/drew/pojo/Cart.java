package com.drew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private String cartID;
    private String cusID;
    private String goodsID;
    private String goodsName;
    private float price;
    private int amount;
    private float total;
    private String statement;
}