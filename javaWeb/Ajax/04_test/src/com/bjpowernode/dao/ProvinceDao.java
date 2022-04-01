package com.bjpowernode.dao;

import com.bjpowernode.entity.Province;
import com.bjpowernode.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceDao {
    public Province QueryById(Integer pid) {
        String sql = "select * from province where id=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        try {
            ps.setInt(1,pid);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                String pname = resultSet.getString("name");
                String jiancheng = resultSet.getString("jiancheng");
                String shenghui = resultSet.getString("shenghui");
                Province province = new Province(pid,pname,jiancheng,shenghui);
                return province;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return null;
    }
}
