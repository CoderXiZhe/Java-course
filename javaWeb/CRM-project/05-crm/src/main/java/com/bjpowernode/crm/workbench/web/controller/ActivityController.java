package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.*;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.Imp.ActivityServiceImp;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    //模板模式
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");
        String path = request.getServletPath();//获取ip地址

        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }else if("/workbench/activity/delete.do".equals(path)){
             delete(request,response);
        }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
            getUserListAndActivity(request,response);
        }else if("/workbench/activity/update.do".equals(path)){
            update(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
            detail(request,response);
        }else if("/workbench/activity/getRemarkListByAid.do".equals(path)){
            getRemarkListByAid(request,response);
        }else if("/workbench/activity/deleteRemark.do".equals(path)){
            deleteRemark(request,response);
        }else if("/workbench/activity/saveRemark.do".equals(path)){
            saveRemark(request,response);
        }else if("/workbench/activity/updateRemark.do".equals(path)){
            updateRemark(request,response);
        }

    }

    private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到市场备注更新");
        String id = request.getParameter("id");
        String noteContent = request.getParameter("noteContent");

        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setEditTime(DateTimeUtil.getSysTime());
        ar.setEditBy(((User) request.getSession().getAttribute("user")).getName());
        ar.setEditFlag("1");
        ar.setNoteContent(noteContent);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());
        boolean flag = as.updateRemark(ar);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);
    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("添加市场活动备注信息");
        String noteContent = request.getParameter("noteContent");
        String activityId = request.getParameter("activityId");
        ActivityRemark ar = new ActivityRemark();
        ar.setId(UUIDUtil.getUUID());
        ar.setActivityId(activityId);
        ar.setCreateBy(((User) request.getSession().getAttribute("user")).getName());
        ar.setCreateTime(DateTimeUtil.getSysTime());
        ar.setNoteContent(noteContent);
        ar.setEditFlag("0");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());
        boolean flag = as.saveRemark(ar);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);

        PrintJson.printJsonObj(response,map);
    }

    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据id删备注");
        String id = request.getParameter("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

        boolean flag = as.deleteRemark(id);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getRemarkListByAid(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据市场活动id 取得备注信息列表");
        String activityId = request.getParameter("activityId");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());
        List<ActivityRemark> remarkList = as.getRemarkListByAid(activityId);

        PrintJson.printJsonObj(response,remarkList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动详情页面");
        String id = request.getParameter("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

        Activity a = as.detail(id);

        request.setAttribute("a",a);

        request.getRequestDispatcher("detail.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动修改操作");
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();


        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setEditTime(editTime);
        activity.setEditBy(editBy);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());
        boolean flag = as.update(activity);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询用户和市场活动新信息");
        String id = request.getParameter("id");

        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

        Map<String,Object> map = activityService.getUserListAndActivity(id);

        PrintJson.printJsonObj(response,map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动删除");
        //获取前端传来的id数组
        String ids[] = request.getParameterValues("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

        boolean flag = activityService.delete(ids);

        PrintJson.printJsonFlag(response,flag);


    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        //获取前端穿的数据
          String pageNoStr = request.getParameter("pageNo");
          int pageNo = Integer.valueOf(pageNoStr);
          String pageSizeStr = request.getParameter("pageSize");
          int pageSize = Integer.valueOf(pageSizeStr);
          String name = request.getParameter("name");
          String owner = request.getParameter("owner");
          String startDate = request.getParameter("startDate");
          String endDate = request.getParameter("endDate");
          int skipCount = (pageNo-1)*pageSize;//分页查询
          Map<String,Object> map = new HashMap<String, Object>();
          map.put("name",name);
          map.put("owner",owner);
          map.put("startDate",startDate);
          map.put("endDate",endDate);
          map.put("skipCount",skipCount);
          map.put("pageSize",pageSize);
          ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());

          PaginationVo<Activity> vo =  activityService.pageList(map);

          PrintJson.printJsonObj(response,vo);


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动添加操作");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        System.out.println(startDate);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());
        boolean flag = as.save(activity);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

       try{
           List<User> uList = userService.getUserList();

           PrintJson.printJsonObj(response,uList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
