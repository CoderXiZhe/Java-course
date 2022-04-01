package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface ClueService {


    boolean save(Clue clue);

    Clue detail(String id);

    List<Activity> getActivityListByClueId(String clueId);

    boolean unbund(String id);

    boolean bund(String[] aids, String clueId);

    boolean convert(String createBy, String clueId, Tran t);

    PaginationVo<Clue> pageList(Map<String, Object> map);
}
