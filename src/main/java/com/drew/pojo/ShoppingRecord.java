package com.drew.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingRecord {
    private String cusID;
    private String goodsID;
    private String goodsName;
    private String image;
    private String time;
    private String status;
    private float price;
    private int amounts;
}
