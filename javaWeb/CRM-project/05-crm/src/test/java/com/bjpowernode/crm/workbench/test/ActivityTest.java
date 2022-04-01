package com.bjpowernode.crm.workbench.test;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.Imp.ActivityServiceImp;
import org.junit.Assert;
import org.junit.Test;



/*
        JUnit : 单元测试
                是未来实际开发 代替main方法的  main方法每次测试一个 都要把其他的注释掉
 */
public class ActivityTest {
    @Test
    public void testSave(){
        Activity activity = new Activity();
        activity.setId(UUIDUtil.getUUID());
        activity.setName("调查问卷");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImp());
        boolean flag = as.save(activity);
        Assert.assertEquals(flag,true);
        System.out.println(flag);
    }


}
