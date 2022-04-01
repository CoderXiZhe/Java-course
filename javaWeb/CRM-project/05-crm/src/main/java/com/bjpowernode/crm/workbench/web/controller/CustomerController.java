package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.service.CustomerService;
import com.bjpowernode.crm.workbench.service.Imp.CustomerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到客户控制器");
        String path = request.getServletPath();
        if("/workbench/customer/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/customer/save.do".equals(path)){
            save(request,response);    
        }else if("/workbench/customer/pageList.do".equals(path)){
            pageList(request,response);
        }else if("/workbench/customer/getUserListAndCustomer.do".equals(path)){
            getUserListAndCustomer(request,response);
        }else if("/workbench/customer/update.do".equals(path)){
            update(request,response);
        }


    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改客户信息");
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String website = request.getParameter("website");
        String phone = request.getParameter("phone");
        String nextContactTime = request.getParameter("nextContactTime");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String contactSummary = request.getParameter("contactSummary");
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editTime = DateTimeUtil.getSysTime();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setOwner(owner);
        customer.setName(name);
        customer.setWebsite(website);
        customer.setPhone(phone);
        customer.setNextContactTime(nextContactTime);
        customer.setDescription(description);
        customer.setAddress(address);
        customer.setContactSummary(contactSummary);
        customer.setEditBy(editBy);
        customer.setEditTime(editTime);

        CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImp());
        boolean flag = customerService.update(customer);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndCustomer(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取用户列表和客户");
        String id = request.getParameter("id");

        CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImp());

        Map<String,Object> map = customerService.getUserListAndCustomer(id);

        PrintJson.printJsonObj(response,map);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("客户分页查询");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");

        int skipCount = (pageNo-1)*pageSize;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("name",name);
        map.put("owner",owner);
        map.put("phone",phone);
        map.put("website",website);
        map.put("skipCount",skipCount);

        CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImp());
        PaginationVo<Customer> vo = customerService.pageList(map);

        PrintJson.printJsonObj(response,vo);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到添加用户");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String website = request.getParameter("website");
        String phone = request.getParameter("phone");
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");

        Customer customer = new Customer();
        customer.setId(UUIDUtil.getUUID());
        customer.setOwner(owner);
        customer.setName(name);
        customer.setWebsite(website);
        customer.setPhone(phone);
        customer.setDescription(description);
        customer.setContactSummary(contactSummary);
        customer.setNextContactTime(nextContactTime);
        customer.setAddress(address);
        customer.setCreateTime(DateTimeUtil.getSysTime());
        customer.setCreateBy(((User)request.getSession().getAttribute("user")).getName());

        CustomerService customerService = (CustomerService) ServiceFactory.getService(new CustomerServiceImp());

        boolean flag = customerService.save(customer);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("得到用户列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response,uList);
    }
}
