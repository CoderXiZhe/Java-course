package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.*;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.domain.TranHistory;
import com.bjpowernode.crm.workbench.service.CustomerService;
import com.bjpowernode.crm.workbench.service.Imp.CustomerServiceImp;
import com.bjpowernode.crm.workbench.service.Imp.TranServiceImp;
import com.bjpowernode.crm.workbench.service.TranService;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到交易控制器");
        String path = request.getServletPath();
        if("/workbench/transaction/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/transaction/getCustomerName.do".equals(path)){
            getCustomerName(request,response);
        }else if("/workbench/transaction/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/transaction/detail.do".equals(path)){
            detail(request,response);
        }else if("/workbench/transaction/getHistoryByTranId.do".equals(path)){
            getHistoryByTranId(request,response);
        }else if("/workbench/transaction/pageList.do".equals(path)){
            pageList(request,response);
        }
        else if("/workbench/transaction/.do".equals(path)){
        }


    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageNo = Integer.valueOf(pageNoStr);
        Integer pageSize = Integer.valueOf(pageSizeStr);
        Integer skipCount = (pageNo-1)*pageSize;
        String owner = request.getParameter("search-owner");
        String name = request.getParameter("search-name");
        String customer = request.getParameter("search-customer");
        String stage = request.getParameter("search-stage");
        String type = request.getParameter("search-type");
        String source = request.getParameter("search-source");
        String contacts = request.getParameter("search-contacts");


        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pageSize",pageSize);
        map.put("skipCount",skipCount);
        map.put("owner",owner);
        map.put("name",name);
        map.put("customer",customer);
        map.put("stage",stage);
        map.put("type",type);
        map.put("source",source);
        map.put("contacts",contacts);

        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImp());
        Map<String,Object> result = tranService.pageList(map);
        PrintJson.printJsonObj(response,result);

    }

    private void getHistoryByTranId(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据id查询交易历史");
        String tranId = request.getParameter("tranId");
        TranService tranService = (TranService) ServiceFactory.getService(new TranServiceImp());

        List<TranHistory> list = tranService.getHistoryByTranId(tranId);

        PrintJson.printJsonObj(response,list);

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到交易详细信息查询");
        String id = request.getParameter("id");
        TranService ts = (TranService) ServiceFactory.getService(new TranServiceImp());
        Tran t = ts.detail(id);

        request.setAttribute("t",t);
        request.getRequestDispatcher("/workbench/transaction/detail.jsp").forward(request,response);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入到交易添加");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String money = request.getParameter("money");
        String name = request.getParameter("name");
        String customerName = request.getParameter("customerName");
        String expectedDate = request.getParameter("expectedDate");
        //String customerId = request.getParameter(""); //现在只有客户名称 没有客户ID
        String stage = request.getParameter("stage");
        String type = request.getParameter("type");
        String source = request.getParameter("source");
        String activityId = request.getParameter("activityId");
        String contactsId = request.getParameter("contactsId");
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        Tran t = new Tran();
        t.setId(id);
        t.setOwner(owner);
        t.setMoney(money);
        t.setName(name);
        t.setExpectedDate(expectedDate);
        t.setStage(stage);
        t.setType(type);
        t.setSource(source);
        t.setActivityId(activityId);
        t.setContactsId(contactsId);
        t.setCreateBy(createBy);
        t.setCreateTime(createTime);
        t.setDescription(description);
        t.setContactSummary(contactSummary);
        t.setNextContactTime(nextContactTime);

        TranService ts = (TranService) ServiceFactory.getService(new TranServiceImp());
        boolean flag = ts.save(t,customerName);
        if(flag) {
            response.sendRedirect(request.getContextPath()+"/workbench/transaction/index.jsp");
        }

    }

    private void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得客户名称 模糊查询");
        String name = request.getParameter("name");

        CustomerService cs = (CustomerService) ServiceFactory.getService(new CustomerServiceImp());
        List<String> sList = cs.getCustomerName(name);
        PrintJson.printJsonObj(response,sList);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("取得用户列表");

        UserService us  = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = us.getUserList();

        request.setAttribute("uList",uList);

        request.getRequestDispatcher("/workbench/transaction/save.jsp").forward(request,response);
    }
}
