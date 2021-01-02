package com.drew.service;

import com.drew.dao.CustomerDao;
import com.drew.pojo.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;

    public List<Customer> findAllCustomer(){
        return customerDao.findAllCustomer();
    }

    public Customer findCustomerByID(String cusID){
        return customerDao.findCustomerByID(cusID);
    }

    public Customer findCustomerByName(String cusName){ return customerDao.findCustomerByName(cusName);}

    public Customer findCustomerByEmail(String cusEmail){ return customerDao.findCustomerByEmail(cusEmail);}

    public boolean addCustomer(Customer customer){
        return customerDao.addCustomer(customer.getCusID(),customer.getCusName(),customer.getCusPwd(),customer.getCusEmail(),customer.getCusTel(),customer.getCusAddress(),customer.getBalance());
    }

    public boolean deleteCustomerByID(String cusID){
        return customerDao.deleteCustomerByID(cusID);
    }

    public boolean editCustomerByID(Customer customer){
        return customerDao.updateCustomerByID(customer.getCusID(),customer.getCusName(),customer.getCusPwd(),customer.getCusEmail(),customer.getCusTel(),customer.getCusAddress(),customer.getBalance());
    }

    public String getCusNameByID(String cusID){
        return customerDao.getCusNameByID(cusID);
    }

    public String getCusIDByName(String cusName){
        return customerDao.getCusIDByName(cusName);
    }

    public boolean updateBalanceByID(String cusID,float balance){
        return customerDao.updateBalanceByID(cusID,balance);
    }

    public float getBalanceByID(String cusID){
        return customerDao.getBalanceByID(cusID);
    }

    public int cnt(){
        List<Customer> customers=customerDao.findAllCustomer();
        return customers.size();
    }

    public boolean isCustomerExist(String cusID){
        if(customerDao.findCustomerByID(cusID)!=null)
            return true;
        else
            return false;
    }
}
