package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueDao {


    int save(Clue clue);

    Clue detail(String id);

    List<Activity> getActivityListByClueId(String clueId);

    Clue getClueById(String clueId);

    int delete(String clueId);

    int getTotal(Map<String, Object> map);

    List<Clue> getDataList(Map<String, Object> map);
}
