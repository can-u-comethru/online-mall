package com.drew.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.drew.pojo.Evaluation;
import com.drew.service.EvaluationService;
import com.drew.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;

    @Resource
    private ShoppingRecordService shoppingRecordService;

    @RequestMapping(value = "/addEvaluation",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addEvaluation(String evaluationID,String cusID, String goodsID, String commentary){
        System.out.println("我添加了"+cusID+" "+goodsID);
        String result = null;
        if(shoppingRecordService.getUserProductRecord(cusID,goodsID)){
            Evaluation evaluation = new Evaluation();
            evaluation.setEvaluationID(evaluationID);
            evaluation.setCusID(cusID);
            evaluation.setGoodsID(goodsID);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            evaluation.setTime(sf.format(date));
            evaluation.setCommentary(commentary);
            evaluationService.addEvaluation(evaluation);
            result = "success";
        }
        else{
            result="noneRecord";
        }

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/findEvaluationByID",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findEvaluationByID(String goodsID){
        List<Evaluation> evaluationList = (List<Evaluation>) evaluationService.findEvaluationByID(goodsID);
        String evaluations = JSONArray.toJSONString(evaluationList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",evaluations);
        return resultMap;
    }

    @RequestMapping(value = "/updateEvaluationByID",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateEvaluationByID(String evaluationID){
        evaluationService.updateEvaluationByID(evaluationService.findEvaluationByID(evaluationID));
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("result","success");
        System.out.println(" 评价已更新！");
        return resultMap;
    }

    @RequestMapping(value = "/deleteEvaluationByID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCartByID(String evaluationID) {
        evaluationService.deleteEvaluationByID(evaluationID);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        System.out.println("我返回了");
        return resultMap;

    }
}
