package com.drew.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    private String evaluationID;
    private String cusID;
    private String goodsID;
    private String commentary;
    private Date evaluationDate;
}
