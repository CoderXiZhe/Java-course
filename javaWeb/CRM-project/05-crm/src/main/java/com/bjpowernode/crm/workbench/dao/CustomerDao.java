package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerDao {

    Customer getCustomerByName(String company);

    int save(Customer customer);

    int getTotal(Map<String, Object> map);

    List<Customer> getDataList(Map<String, Object> map);

    Customer getById(String id);

    int update(Customer customer);

    List<String> getCustomerName(String name);
}
