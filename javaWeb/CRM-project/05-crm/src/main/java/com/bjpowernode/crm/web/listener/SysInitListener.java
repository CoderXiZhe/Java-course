package com.bjpowernode.crm.web.listener;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.settings.service.impl.DicServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {
    /*
        该方法是监听上下文对象 即全局作用域对象 服务器启动时 上下文对象创建
        然后执行该方法
     */
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("服务器缓存数据字典开始");

        ServletContext application = event.getServletContext();

        DicService ds = (DicService) ServiceFactory.getService(new DicServiceImpl());

        Map<String, List<DicValue>> map =  ds.getAll();

        Set<String> set = map.keySet();

        for (String key:set){

            application.setAttribute(key,map.get(key));
        }

    }
}
