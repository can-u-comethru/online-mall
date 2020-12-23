package com.drew.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String cusID;
    private String cusName;
    private String cusPwd;
    private String cusEmail;
    private String cusTel;
    private String cusAddress;
    private float balance;
}
