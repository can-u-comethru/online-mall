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

    public String getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(String evaluationID) {
        this.evaluationID = evaluationID;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
