package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);
    int countOfActivityByCondition(Map<String,Object> map);
    int deleteActivityByIds(String ids[]);
    Activity queryActivityById(String id);
    int updateActivityById(Activity activity);
}
