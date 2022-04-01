package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    List<User> getUserList();

    int save(Activity activity);

    List<Activity> getDataList(Map<String, Object> map);

    int getTotal(Map<String, Object> map);

    int delete(String[] ids);

    Activity getById(String id);

    int update(Activity activity);

    Activity detail(String id);

    List<Activity> getActivityListAndNotByClueId(Map<String, String> map);

    List<Activity> getActivityList(String name);
}
