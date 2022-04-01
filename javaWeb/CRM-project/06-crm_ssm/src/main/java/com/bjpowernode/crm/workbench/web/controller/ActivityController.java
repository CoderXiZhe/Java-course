package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String activityIndex(HttpServletRequest request){
        List<User> uList = userService.QueryAllUser();
        request.setAttribute("uList",uList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateBy(user.getId());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        //凡是添加的都要加上异常处理
        try {
            int count = activityService.saveCreateActivity(activity);
            if(count!=1){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍候重试!");
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,请稍候重试!");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/pagination.do")
    @ResponseBody
    public Object pagination(String name,String owner,String startDate,String endDate,
                             Integer pageNo,Integer pageSize){

        // 获取参数后封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        Map<String,Object> returnMap = new HashMap<>();
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.countOfActivityByCondition(map);
        returnMap.put("activityList",activityList);
        returnMap.put("totalRows",totalRows);
        return returnMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    @ResponseBody
    public Object deleteActivityByIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try{
            int ret = activityService.deleteActivityByIds(id);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        Activity activity = new Activity();
        activity = activityService.queryActivityById(id);
        return activity;
    }

    @RequestMapping("/workbench/activity/updateActivityById.do")
    @ResponseBody
    public Object updateActivityById(Activity activity, HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        activity.setEditBy(user.getName());
        activity.setEditTime(DateUtils.formatDateTime(new Date()));
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityService.updateActivityById(activity);
            if(ret==1){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍候再试...");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return returnObject;
    }


}
