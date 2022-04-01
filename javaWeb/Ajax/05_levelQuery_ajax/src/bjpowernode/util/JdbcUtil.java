package bjpowernode.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class JdbcUtil {
        private static String url = "";
        private static String user = "";
        private static String password = "";
        static Connection con = null;
        //数据库的连接要晚 关闭要早
    //-----------------------通过全局作用域对象得到connection---------------------
        public static  Connection getConn(HttpServletRequest request) {
           ServletContext application =  request.getServletContext();
           Map map =(Map)application.getAttribute("key");
           Iterator it = map.keySet().iterator();
           while(it.hasNext()) {
               con = (Connection)it.next();
               boolean flag = (boolean)map.get(con);
               if(flag == true) {
                   map.put(con,false);
                   break;
               }
           }
           return con;
        }
    public static PreparedStatement createStatement(String sql,HttpServletRequest request) {
        try {
            PreparedStatement ps = getConn(request).prepareStatement(sql);
            return ps;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void close(Statement statement, ResultSet resultSet,HttpServletRequest request){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("key");
        map.put(con,true);
    }
    //-----------------------通过全局作用域对象得到connection---------------------
        static{
            Properties ppt = new Properties();
                InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
            try {
                ppt.load(is);
                url = ppt.getProperty("url");
                user = ppt.getProperty("user");
                password = ppt.getProperty("password");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        /**
         * 用于链接数据库，得到的结果是数据库的连接对象，链接对象具备了操作数据库的很多功能。=
         * @return 链接对象
         */
        public static Connection getConn(){

            //1.    获取数据库链接
            try {
                Connection conn = DriverManager.getConnection(url,user,password);
                //3.    将链接返回给工具的使用者
                return conn;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }


        public static PreparedStatement createStatement(String sql) {
            try {
                PreparedStatement ps = getConn().prepareStatement(sql);
                return ps;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

        /**
         * 断开数据库资源的链接，用于释放资源
         * @param conn 要释放链接
         * @param statement 要释放的执行环境
         * @param resultSet 要释放的结果集
         */
        public static void close(Connection conn, Statement statement, ResultSet resultSet){
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


}

