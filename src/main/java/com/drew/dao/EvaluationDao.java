package com.drew.dao;

import com.drew.pojo.Evaluation;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface EvaluationDao {
    @Select("select * from evaluation")
    List<Evaluation> findAllEvaluation();

    @Select("select * from evaluation where evaluationID=#{evaluationID}")
    Evaluation findEvaluationByID(@Param("evaluationID") String evaluationID);

    @Insert("insert into evaluation(evaluationID,cusID,goodsID,commentary,evaluationDate) values(#{evaluationID},#{cusID},#{goodsID},#{commentary},#{evaluationDate})")
    boolean addEvaluation(@Param("evaluationID") String evaluationID, @Param("cusID") String cusID, @Param("goodsID") String goodsID, @Param("commentary") String commentary, @Param("evaluationDate") Date evaluationDate);

    @Update("update evaluation set commentary=#{commentary},evaluationDate=#{evaluationDate} where evaluationID=#{evaluationID}")
    boolean updateEvaluationByID(@Param("evaluationID") String evaluationID,@Param("commentary") String commentary,@Param("evaluationDate") Date evaluationDate);

    @Delete("delete from evaluation where evaluationID=#{evaluationID}")
    boolean deleteEvaluationByID(@Param("evaluationID") String evaluationID);
}
