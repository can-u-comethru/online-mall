package com.drew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {
    private String orderFormID;
    private String cusID;
    private float sum;
    private String status;
    private Date time;
}
