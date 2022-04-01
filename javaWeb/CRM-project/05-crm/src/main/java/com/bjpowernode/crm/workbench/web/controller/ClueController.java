package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.Imp.ActivityServiceImp;
import com.bjpowernode.crm.workbench.service.Imp.ClueServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {
    //模板模式
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到线索控制器");
        String path = request.getServletPath();
        if("/workbench/clue/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/clue/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/clue/detail.do".equals(path)){
            detail(request,response);
        }else if("/workbench/clue/getActivityListByClueId.do".equals(path)){
            getActivityListByClueId(request,response);
        }else if("/workbench/clue/unbund.do".equals(path)){
            unbund(request,response);
        }else if("/workbench/clue/getActivityListAndNotByClueId.do".equals(path)){
            getActivityListAndNotByClueId(request,response);
        }else if("/workbench/clue/bund.do".equals(path)){
            bund(request,response);
        }else if("/workbench/clue/getActivityList.do".equals(path)){
            getActivityList(request,response);
        }else if("/workbench/clue/convert.do".equals(path)){
            convert(request,response);
        }else if("/workbench/clue/pageList.do".equals(path)){
            pageList(request,response);
        }



    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("分页查询线索记录");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        int skipCount = (pageNo-1)*pageSize;
        String fullname = request.getParameter("fullname");
        String company = request.getParameter("company");
        String phone = request.getParameter("phone");
        String source = request.getParameter("source");
        String owner = request.getParameter("owner");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("fullname",fullname);
        map.put("company",company);
        map.put("phone",phone);
        map.put("source",source);
        map.put("owner",owner);
        map.put("mphone",mphone);
        map.put("state",state);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImp());

        PaginationVo<Clue> vo =  cs.pageList(map);

        PrintJson.printJsonObj(response,vo);




    }

    private void convert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行线索转换任务");
        String clueId = request.getParameter("clueId");
        String a = request.getParameter("a");  //flag表示提交了表单 即创建了交易
        String createBy =((User)request.getSession().getAttribute("user")).getName();
        Tran t = null;
        if("a".equals(a)){
            String money = request.getParameter("money");
            String name = request.getParameter("name");
            String expectedDate = request.getParameter("expectedDate");
            String stage = request.getParameter("stage");
            String activityId = request.getParameter("activityId");
            String id = UUIDUtil.getUUID();

            String createTime = DateTimeUtil.getSysTime();
            t = new Tran();
            t.setId(id);
            t.setActivityId(activityId);
            t.setName(name);
            t.setMoney(money);
            t.setExpectedDate(expectedDate);
            t.setStage(stage);
            t.setCreateBy(createBy);
            t.setCreateTime(createTime);
        }
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImp());
        boolean flag = cs.convert(createBy,clueId,t);

        //传统方式 重定向或请求转发  用到request域时候用请求转发 不然用重定向 这样不会停留在旧的页面
        if(flag) {
            response.sendRedirect(request.getContextPath() +"/workbench/clue/index.jsp");
        }

    }

    private void getActivityList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询市场活动列表");
        String name = request.getParameter("aname");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

        List<Activity> aList = as.getActivityList(name);

        PrintJson.printJsonObj(response,aList);
    }

    private void bund(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到关联市场活动");
        String cid = request.getParameter("cid");
        String[] aids = request.getParameterValues("aid");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImp());

        boolean flag  = cs.bund(aids, cid);
        PrintJson.printJsonFlag(response,flag);

    }

    private void getActivityListAndNotByClueId(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("查询市场活动列表但不包括已关联");

        String aname = request.getParameter("name");
        String clueId = request.getParameter("id");
        Map<String,String> map = new HashMap<String, String>();
        map.put("aname",aname);
        map.put("clueId",clueId);

        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

        List<Activity> aList = activityService.getActivityListAndNotByClueId(map);

        PrintJson.printJsonObj(response,aList);

    }

    private void unbund(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("解除市场关联");
        String id = request.getParameter("id");

        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImp());

        boolean flag = clueService.unbund(id);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getActivityListByClueId(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("线索与市场关联");
        String clueId = request.getParameter("id");

        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImp());

        List<Activity> aList = clueService.getActivityListByClueId(clueId);

        PrintJson.printJsonObj(response,aList);

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到线索详情页");
        String id = request.getParameter("id");

        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImp());
        Clue c = clueService.detail(id);
        c.setId(id);
        request.setAttribute("c",c);
        request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request,response);//请求转发  用EL表达式
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到添加线索");
        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");

        Clue clue = new Clue();
        clue.setId(id);
        clue.setAddress(address);
        clue.setAppellation(appellation);
        clue.setCompany(company);
        clue.setContactSummary(contactSummary);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        clue.setDescription(description);
        clue.setEmail(email);
        clue.setJob(job);
        clue.setMphone(mphone);
        clue.setFullname(fullname);
        clue.setOwner(owner);
        clue.setPhone(phone);
        clue.setWebsite(website);
        clue.setState(state);
        clue.setSource(source);
        clue.setNextContactTime(nextContactTime);
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImp());
        boolean flag = clueService.save(clue);
        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = userService.getUserList();  //这里可以复用

        PrintJson.printJsonObj(response,uList);

    }


}
