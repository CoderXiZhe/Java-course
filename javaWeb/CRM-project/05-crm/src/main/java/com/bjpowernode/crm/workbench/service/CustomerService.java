package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    boolean save(Customer customer);

    PaginationVo<Customer> pageList(Map<String, Object> map);

    Map<String, Object> getUserListAndCustomer(String id);

    boolean update(Customer customer);

    List<String> getCustomerName(String name);
}
