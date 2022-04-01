package com.bjpowernode.dao;

import com.bjpowernode.entity.Province;
import com.bjpowernode.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceDao {
    public String queryProvinceNameById(Integer ProvinceId) {
        String sql="select * from province where id=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        String pname ="";

        try {
            ps.setInt(1,ProvinceId);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                pname = resultSet.getString("name");
                return pname;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return null;
    }

    public Province queryProvinceNameById2(Integer ProvinceId) {
        String sql="select * from province where id=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        try {
            ps.setInt(1,ProvinceId);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("name");
                String jiancheng = resultSet.getString("jiancheng");
                String shenghui = resultSet.getString("shenghui");
                Province province = new Province(ProvinceId,name,jiancheng,shenghui);
                return province;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return null;
    }
}
