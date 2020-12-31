package com.drew.dao;

import com.drew.pojo.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerDao {
    @Select("select * from customer")
    List<Customer> findAllCustomer();

    @Select("select * from customer where cusID=#{cusID}")
    Customer findCustomerByID(@Param("cusID") String cusID);

    @Select("select * from customer where cusName=#{cusName}")
    Customer findCustomerByName(@Param("cusName") String cusName);

    @Select("select * from customer where cusName=#{cusEmail}")
    Customer findCustomerByEmail(@Param("cusName") String cusEmail);

    @Insert("insert into customer(cusID,cusName,cusPwd,cusEmail,cusTel,cusAddress,balance) values(#{cusID},#{cusName},#{cusPwd},#{cusEmail},#{cusTel},#{cusAddress},#{balance})")
    boolean addCustomer(@Param("cusID") String cusID,@Param("cusName") String cusName,@Param("cusPwd") String cusPwd,@Param("cusEmail") String cusEmail,@Param("cusTel") String cusTel,@Param("cusAddress") String cusAddress,@Param("balance") Float balance);

    @Update("update customer set cusName=#{cusName},cusPwd=#{cusPwd},cusEmail=#{cusEmail},cusTel=#{cusTel},cusAddress=#{cusAddress},Balance=#{Balance} where cusID=#{cusID}")
    boolean updateCustomerByID(@Param("cusID") String cusID,@Param("cusName") String cusName,@Param("cusPwd") String cusPwd,@Param("cusEmail") String cusEmail,@Param("cusTel") String cusTel,@Param("cusAddress") String cusAddress,@Param("Balance")Float Balance);

    @Delete("delete from customer where cusID=#{cusID}")
    boolean deleteCustomerByID(@Param("cusID") String cusID);

    @Select("select cusName from customer where cusID=#{cusID}")
    String getCusNameByID(@Param("cusID") String cusID);

    @Update("update customer set balance=#{balance} where cusID=#{cusID}")
    boolean updateBalanceByID(@Param("cusID") String cusID,@Param("balance") float balance);

    @Select("select balance from customer where cusID=#{cusID}")
    float getBalanceByID(@Param("cusID") String cusID);
}
