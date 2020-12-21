package com.drew.service;

import com.drew.dao.AdminDao;
import com.drew.pojo.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public List<Admin> findAllAdmin(){
        return adminDao.findAllAdmin();
    }

    public Admin findAdminByID(String adminID){
        return adminDao.findAdminByID(adminID);
    }

    public boolean isAdminExist(String adminID){
        if(adminDao.findAdminByID(adminID)!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addAdmin(Admin admin){
        return adminDao.addAdmin(admin.getAdminID(),admin.getAdminName(),admin.getAdminPwd(),admin.getAdminEmail(),admin.getAdminTel(), (String) admin.getSupervisor());
    }

    public boolean deleteAdminByID(String adminID){
        return adminDao.deleteAdminByID(adminID);
    }

    public boolean editAdminByID(Admin admin){
        return adminDao.updateAdminByID(admin.getAdminID(),admin.getAdminName(),admin.getAdminPwd(),admin.getAdminEmail(),admin.getAdminTel(), (String) admin.getSupervisor());
    }
}
