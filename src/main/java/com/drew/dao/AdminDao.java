package com.drew.dao;

import com.drew.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminDao {
    @Select("select * from admin")
    List<Admin> findAllAdmin();

    @Select("select * from admin where adminID=#{adminID}")
    Admin findAdminByID(@Param("adminID") String adminID);

    @Insert("insert into admin(adminID,adminName,adminPwd,adminEmail,adminTel) values(#{adminID},#{adminName},#{adminPwd},#{adminEmail},#{adminTel})")
    boolean addAdmin(@Param("adminID") String adminID,@Param("adminName") String adminName,@Param("adminPwd") String adminPwd,@Param("adminEmail") String adminEmail,@Param("adminTel") String adminTel);

    @Update("update admin set adminName=#{adminName},adminPwd=#{adminPwd},adminEmail=#{adminEmail},adminTel=#{adminTel} where adminID=#{adminID}")
    boolean updateAdminByID(@Param("adminID") String adminID,@Param("adminName") String adminName,@Param("adminPwd") String adminPwd,@Param("adminEmail") String adminEmail,@Param("adminTel") String adminTel);

    @Delete("delete from admin where adminID=#{adminID}")
    boolean deleteAdminByID(@Param("adminID") String adminID);
}
