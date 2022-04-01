package com.bjpowernoder.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OneListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("被初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("被摧毁");
    }
}
