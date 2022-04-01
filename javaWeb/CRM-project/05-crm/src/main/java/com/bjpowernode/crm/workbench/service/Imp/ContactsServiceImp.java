package com.bjpowernode.crm.workbench.service.Imp;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.ContactsDao;

public class ContactsServiceImp {
    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
}
