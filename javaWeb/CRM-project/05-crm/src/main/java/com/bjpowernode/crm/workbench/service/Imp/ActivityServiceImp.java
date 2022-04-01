package com.bjpowernode.crm.workbench.service.Imp;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImp implements ActivityService {
    ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    public boolean save(Activity activity) {
     boolean flag = true;
     int count  = activityDao.save(activity);
     if(count == 0) {
         flag = false;
     }
     return flag;
    }

    public PaginationVo<Activity> pageList(Map<String, Object> map) {

        //获取total
        int total = activityDao.getTotal(map);//记得传参
        //获取dataList
        List<Activity> datalist = activityDao.getDataList(map);//记得传参

        //创建一个vo对象
        PaginationVo<Activity> vo  = new PaginationVo<Activity>();
        vo.setTotal(total);
        vo.setDataList(datalist);

        return vo;

    }

    public boolean delete(String[] ids) {
        boolean flag = true;
        //删除市场活动之前 要先删除 对应的备注信息

        int count1 = activityRemarkDao.getCountByAids(ids);//查询出市场备注的数量

        int count2 = activityRemarkDao.deleteCountByAids(ids);//实际删除的数量

        if(count1!=count2){
            //备注的 查询数量不等于删除数量    删除失败  事务回滚在动态代理中处理了
            flag = false;
        }
        int count3 = activityDao.delete(ids);//删除市场活动的记录数

        if(ids.length != count3){
            flag=false;
        }

      return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {
        //获取uList
        List<User> uList = userDao.getUserList();
        //获取a
        Activity a = activityDao.getById(id);
        //添加到map
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("uList",uList);
        map.put("a",a);

        return map;
    }

    public boolean update(Activity activity) {
        boolean flag = true;
        int count  = activityDao.update(activity);
        if(count == 0) {
            flag = false;
        }
        return flag;
    }

    public Activity detail(String id) {
        Activity a = activityDao.detail(id);

        return a;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        List<ActivityRemark> remarkList = activityRemarkDao.getRemarkListByAid(activityId);
        return remarkList;
    }

    public boolean deleteRemark(String id) {
        boolean flag = true;
        int count = activityRemarkDao.deleteRemark(id);

        if(count !=1) {
            flag = false;
        }

        return flag;
    }

    public boolean saveRemark(ActivityRemark ar) {
        boolean flag = true;
        int count = activityRemarkDao.saveRemark(ar);
        if(count!=1) {
            flag = false;
        }
        return flag;
    }

    public boolean updateRemark(ActivityRemark ar) {
        boolean flag = true;

        int count = activityRemarkDao.updateRemark(ar);
        if(count != 1) {
            flag = false;
        }
        return flag;
    }

    public List<Activity> getActivityListAndNotByClueId(Map<String, String> map) {
        List<Activity> aList = activityDao.getActivityListAndNotByClueId(map);

        return aList;
    }

    public List<Activity> getActivityList(String name) {

        List<Activity> aList = activityDao.getActivityList(name);

        return aList;
    }
}
