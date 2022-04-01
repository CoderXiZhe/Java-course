package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.dao.DicTypeDao;
import com.bjpowernode.crm.settings.dao.DicValueDao;
import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    public Map<String, List<DicValue>> getAll() {

        //一对多查询
        List<DicType> dtList = dicTypeDao.getTypeList();//获取数据字典类型所有类别
        Map<String,List<DicValue>> map = new HashMap<String, List<DicValue>>();
        for(DicType dt:dtList){
            String code = dt.getCode();//得到类别的名称
            List<DicValue> dvList = dicValueDao.getValueListByCode(code);//根据名称查找对应值
            map.put(code,dvList);
        }
        return map;
    }
}
