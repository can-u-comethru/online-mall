package com.drew.service;

import com.drew.dao.EvaluationDao;
import com.drew.pojo.Evaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EvaluationService {
    @Resource
    private EvaluationDao evaluationDao;

    public List<Evaluation> findAllEvaluation(){
        return evaluationDao.findAllEvaluation();
    }

    public Evaluation findEvaluationByID(String evaluationID){
        return evaluationDao.findEvaluationByID(evaluationID);
    }

    public boolean isEvaluationExist(String evaluationID){
        if(evaluationDao.findEvaluationByID(evaluationID)!=null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addEvaluation(Evaluation evaluation){
        return evaluationDao.addEvaluation(evaluation.getEvaluationID(),evaluation.getCusID(),evaluation.getGoodsID(),evaluation.getCommentary(),evaluation.getEvaluationDate());
    }

    public boolean deleteEvaluationByID(String evaluationID){
        return evaluationDao.deleteEvaluationByID(evaluationID);
    }

    public boolean editEvaluationByID(Evaluation evaluation){
        return evaluationDao.updateEvaluationByID(evaluation.getEvaluationID(),evaluation.getCommentary(),evaluation.getEvaluationDate());
    }

    public int cnt(){
        List<Evaluation> evaluations=evaluationDao.findAllEvaluation();
        return evaluations.size();
    }
}
