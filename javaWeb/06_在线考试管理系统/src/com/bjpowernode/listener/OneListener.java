package com.bjpowernode.listener;

import com.bjpowernode.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneListener implements ServletContextListener {
    //在Tomcat启动时 预先创建20个Connection 在userDao.add方法执行时候 将connection交给add
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Map map = new HashMap();
        for(int i=1;i<=20;i++) {
            Connection con  = JdbcUtil.getConn();
            System.out.println("在http服务器启动时候 创建Connection" + con);
            map.put(con,true);//true表示这个通道处于空闲状态 , false表示正在被使用
        }
        //为了Http服务器在运行期间 一直都可以使用20个Connection 将connection保存在全局作用于对象
        ServletContext application = sce.getServletContext();
        application.setAttribute("key",map);
    }//map被销毁

    //http服务器关闭时候 将全局作用域对象20个connection销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map =(Map)application.getAttribute("key");
        Iterator it = map.keySet().iterator();
        while(it.hasNext()) {
            Connection con = (Connection) it.next();
            if(con!=null) {
                System.out.println(con + "被摧毁   ");
            }
        }
    }
}
