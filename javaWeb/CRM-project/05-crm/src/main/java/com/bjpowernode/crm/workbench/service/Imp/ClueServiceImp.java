package com.bjpowernode.crm.workbench.service.Imp;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.*;
import com.bjpowernode.crm.workbench.domain.*;
import com.bjpowernode.crm.workbench.service.ClueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueServiceImp implements ClueService {
    //线索
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
    //联系人
    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    private ContactsRemarkDao contactsRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);
    private ContactsActivityRelationDao contactsActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ContactsActivityRelationDao.class);
    //客户
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);
    //交易
    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);


    public boolean save(Clue clue) {
        boolean flag = true;
        int count = clueDao.save(clue);
        if(count !=1) {
            flag = false;
        }
        return flag;
    }

    public Clue detail(String id) {
       Clue c =  clueDao.detail(id);
       return c;
    }

    public List<Activity> getActivityListByClueId(String clueId) {
       List<Activity> aList =  clueDao.getActivityListByClueId(clueId);//dao用错了
       return aList;
    }

    public boolean unbund(String id) {
        boolean flag = true;
        int count = clueActivityRelationDao.unbund(id);

        if(count != 1) {
            flag = false;
        }
        return flag;
    }

    public boolean bund(String[] aids, String clueId) {
        boolean flag = true;

        for(String aid:aids){
                ClueActivityRelation car = new ClueActivityRelation();
                car.setId(UUIDUtil.getUUID());
                car.setClueId(clueId);
                car.setActivityId(aid);
                int count = clueActivityRelationDao.bund(car);

                if (count != 1) {
                    flag = false;
                }
        }
        return flag;
    }

    public boolean convert(String createBy, String clueId, Tran t) {
        String createTime = DateTimeUtil.getSysTime();
        boolean flag = true;
        //(1) 获取到线索id，通过线索id获取线索对象（线索对象当中封装了线索的信息）
        Clue clue = clueDao.getClueById(clueId);

        //(2) 通过线索对象提取客户信息，当该客户不存在的时候，新建客户（根据公司的名称精确匹配，判断该客户是否存在！）
        String company = clue.getCompany(); //客户名
        Customer customer = customerDao.getCustomerByName(company);
        if(customer == null){
            customer = new Customer();//没有这个客户 就为他新建一个  客户的信息也就是原来线索中的信息 线索就是潜在的客户
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateBy(createBy);
            customer.setCreateTime(createTime);
            customer.setAddress(clue.getAddress());
            customer.setName(company);
            customer.setContactSummary(clue.getContactSummary());
            customer.setOwner(clue.getOwner());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setDescription(clue.getDescription());
            customer.setNextContactTime(clue.getNextContactTime());

            //添加客户
            int count1 = customerDao.save(customer);
            if(count1 != 1){
                //return false;
                flag = false;
            }
        }

        //(3) 通过线索对象提取联系人信息，保存联系人
        String fullname = clue.getFullname();//线索的联系人名字
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setEmail(clue.getEmail());
        contacts.setAppellation(clue.getAppellation());
        contacts.setCreateBy(createBy);
        contacts.setCreateTime(createTime);
        contacts.setJob(clue.getJob());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setMphone(clue.getMphone());
        contacts.setCustomerId(customer.getId());
        contacts.setOwner(clue.getOwner());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setSource(clue.getSource());
        contacts.setDescription(clue.getDescription());
        //添加联系人
        int count2 = contactsDao.save(contacts);
        if(count2 != 1){
            //return false;
            flag = false;
        }

        //(4) 线索备注转换到客户备注以及联系人备注
        //根据线索id查询出线索关联的所有备注信息
        List<ClueRemark> clueRemarkList = clueRemarkDao.getListByClueId(clueId);
        for(ClueRemark clueRemark:clueRemarkList){
            //拿到每一条备注
            String noteContent = clueRemark.getNoteContent();//主要转换的就是这个
            //创建客户备注对象
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateBy(createBy);
            customerRemark.setCreateTime(createTime);
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setEditFlag("0");
            customerRemark.setNoteContent(noteContent);
            //向数据库添加客户备注
            int count3 = customerRemarkDao.save(customerRemark);
            if(count3 !=1){
                //return false;
                flag = false;
            }
            //创建联系人备注对象
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setCreateBy(createBy);
            contactsRemark.setCreateTime(createTime);
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setEditFlag("0");
            contactsRemark.setNoteContent(noteContent);
            //向数据库添加联系人备注
            int count4 = contactsRemarkDao.save(contactsRemark);
            if(count4 !=1){
                //return false;
                flag = false;
            }

        }
        //(5) “线索和市场活动”的关系转换到“联系人和市场活动”的关系
        //根据线索id查询所关联的市场活动id
        List<String> aidList = clueActivityRelationDao.getActivityId(clueId);
        for(String aid:aidList){
            //创建联系人和市场活动联系对象
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(aid);
            contactsActivityRelation.setContactsId(contacts.getId());

            int count5 = contactsActivityRelationDao.save(contactsActivityRelation);
            if(count5 !=1){
                //return false;
                flag = false;
            }

        }


        //(6) 如果有创建交易需求，创建一条交易
        //t不为空 表明有交易需求
        if(t != null){
            /*
                    t已经在controller封装过了 但是没有完全
                    将t给补完全了
             */
            t.setSource(clue.getSource());
            t.setOwner(clue.getOwner());
            t.setNextContactTime(clue.getNextContactTime());
            t.setDescription(clue.getDescription());
            t.setCustomerId(customer.getId());
            t.setContactSummary(clue.getContactSummary());
            t.setContactsId(contacts.getId());
            int count6 = tranDao.save(t);
            if(count6 !=1){
                //return false;
                flag = false;
            }
        }

        //(7) 如果创建了交易，则创建一条该交易下的交易历史
        if(t != null){
            TranHistory tranHistory = new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateBy(createBy);
            tranHistory.setCreateTime(createTime);
            tranHistory.setMoney(t.getMoney());
            tranHistory.setExpectedDate(t.getExpectedDate());
            tranHistory.setTranId(t.getId());
            tranHistory.setStage(t.getStage());

            int count7 = tranHistoryDao.save(tranHistory);
            if(count7 != 1){
                //return false;
                flag = false;
            }
        }

        //(8) 删除线索备注
        for(ClueRemark clueRemark:clueRemarkList){

            int count8 =clueRemarkDao.delete(clueRemark.getId());

            if(count8 != 1){
                //return false;
                flag = false;
            }
        }


        //(9) 删除线索和市场活动的关系
        for(String activityId:aidList){
            int count9 = clueActivityRelationDao.deleteByAid(activityId);
            if(count9 != 1){
                //return false;
                flag = false;
            }
        }

        //(10) 删除线索
        int count10 = clueDao.delete(clueId);
        if(count10 != 1){
            //return false;
            flag = false;
        }



        return flag;
    }

    public PaginationVo<Clue> pageList(Map<String, Object> map) {

        //获取总条数
        int total = clueDao.getTotal(map);

        //获取dataList
        List<Clue> dataList = clueDao.getDataList(map);

        PaginationVo<Clue> vo = new PaginationVo<Clue>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        return vo;
    }
}
