package com.bjpowernode.dao;

import com.bjpowernode.entity.Users;
import com.bjpowernode.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public int add(Users user) {
        //Connection的创建和销毁最浪费时间
        //1.连接数据库
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = null;
        ResultSet result = null;

        String sql ="insert into users(userName,password,sex,email) values(?,?,?,?)";
        ps = JdbcUtil.createStatement(sql);
        int result2 = 0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result2 = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,result);
        }
        return result2;
    }

    public int add(Users user, HttpServletRequest request) {
        //Connection的创建和销毁最浪费时间
        //1.连接数据库
        //Connection conn = JdbcUtil.getConn(request);
        PreparedStatement ps = null;
        //ResultSet result = null;

        String sql ="insert into users(userName,password,sex,email) values(?,?,?,?)";
        ps = JdbcUtil.createStatement(sql,request);
        int result2 = 0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result2 = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(ps,null,request);
        }
        return result2;
    }

    public List findAll() {
        Connection conn = null;
        String sql = "select * from users";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        List userlist = new ArrayList();
        try {
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                Integer userId  = resultSet.getInt("userId");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String sex = resultSet.getString("sex");
                String email = resultSet.getString("email");
                Users user = new Users(userId,userName,password,sex,email);
                userlist.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,resultSet);
        }
        return userlist;
    }

    public int delete(String userId){
        String sql="delete from users where userId=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        int result =0;
        try {
            ps.setInt(1, Integer.parseInt(userId));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(null,ps,null);
        }
        return result;
    }

    public int login(String userName,String password) {
        String sql = "select count(*) from users where userName=? and password=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        int result = 0;
        try {
            ps.setString(1,userName);
            ps.setString(2,password);
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                result = resultSet.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return result;
    }
}
