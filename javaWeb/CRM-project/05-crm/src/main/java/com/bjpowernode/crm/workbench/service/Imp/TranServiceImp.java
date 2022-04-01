package com.bjpowernode.crm.workbench.service.Imp;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.dao.TranDao;
import com.bjpowernode.crm.workbench.dao.TranHistoryDao;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.domain.TranHistory;
import com.bjpowernode.crm.workbench.service.TranService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranServiceImp implements TranService {
    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);
    public boolean save(Tran t, String customerName) {
        boolean flag = true;
        //检查是否有该顾客 如果有则取他的id赋给t   没有则新建 id赋给t
        Customer customer = customerDao.getCustomerByName(customerName);
        if(customer==null){
            //没有这个客户 新建一个
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(customerName);
            customer.setCreateBy(t.getCreateBy());
            customer.setCreateTime(t.getCreateTime());
            customer.setContactSummary(t.getContactSummary());
            customer.setNextContactTime(t.getNextContactTime());
            customer.setDescription(t.getDescription());
            customer.setOwner(t.getOwner());
            int count = customerDao.save(customer);
            if(count != 1 ){
                flag = false;
            }
        }
        //添加交易  ,此时客户已经有了 要设置交易的id
        t.setCustomerId(customer.getId());
        int count2 = tranDao.save(t);
        if(count2 != 1) {
            flag = false;
        }
        //创建交易历史
        TranHistory th = new TranHistory();
        th.setId(UUIDUtil.getUUID());
        th.setTranId(t.getId());
        th.setCreateTime(t.getCreateTime());
        th.setCreateBy(t.getCreateBy());
        th.setMoney(t.getMoney());
        th.setStage(t.getStage());
        th.setExpectedDate(t.getExpectedDate());
        int count3 = tranHistoryDao.save(th);
        if(count3 != 1) {
            flag = false;
        }
        return flag;
    }

    public Tran detail(String id) {
        Tran tran = tranDao.getById(id);

        return tran;
    }

    public List<TranHistory> getHistoryByTranId(String tranId) {
        List<TranHistory> list = tranHistoryDao.getHistoryByTranId(tranId);

        return list;
    }

    public Map<String, Object> pageList(Map<String, Object> map) {
        Map<String,Object> result = new HashMap<String, Object>();
        //获取total
        Integer total = tranDao.getTotal(map);
        //获取dataList
        List<Tran> list = tranDao.getDataList(map);

        map.put("total",total);
        map.put("dataList",list);
        return map;
    }
}
