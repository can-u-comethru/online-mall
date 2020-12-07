package com.drew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String adminID;
    private String adminName;
    private String adminPwd;
    private String adminEmail;
    private String adminTel;
}
