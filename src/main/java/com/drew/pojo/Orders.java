package com.drew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private String orderFormID;
    private String cusID;
    private float sum;
    private String status;
    private Date orderFormDate;
}
