package com.bjpowernode.crm.workbench.service.Imp;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImp implements CustomerService {
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    public boolean save(Customer customer) {
        boolean flag = true;
        int count = customerDao.save(customer);
        if(count != 1){
            flag = false;
        }

        return flag;
    }

    public PaginationVo<Customer> pageList(Map<String, Object> map) {
        PaginationVo<Customer> vo = new PaginationVo<Customer>();
        int total = customerDao.getTotal(map);

        List<Customer> dataList = customerDao.getDataList(map);

        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;


    }

    public Map<String, Object> getUserListAndCustomer(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> uList = userDao.getUserList();
        Customer customer = customerDao.getById(id);
        map.put("uList",uList);
        map.put("customer",customer);
        return map;
    }

    public boolean update(Customer customer) {
        boolean flag = true;
        int count = customerDao.update(customer);
        if(count!=1){
            flag = false;
        }
        return flag;
    }

    public List<String> getCustomerName(String name) {

       List<String> sList =  customerDao.getCustomerName(name);

       return sList;

    }
}
