package com.drew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private String goodsID;
    private String goodName;
    private String description;
    private String category;
    private float price;
    private int stock;
    private String image;
}
