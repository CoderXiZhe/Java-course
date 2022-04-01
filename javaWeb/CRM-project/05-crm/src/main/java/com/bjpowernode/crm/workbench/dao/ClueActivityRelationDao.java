package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {


    int unbund(String id);



    int bund(ClueActivityRelation car);

    List<String> getActivityId(String clueId);

    int deleteByAid(String activityId);
}
