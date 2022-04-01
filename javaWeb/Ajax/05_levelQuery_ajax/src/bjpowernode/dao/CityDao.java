package bjpowernode.dao;

import bjpowernode.entity.City;
import bjpowernode.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    public List queryByPid(Integer pid) {
        List list = new ArrayList();
        String sql="select * from city where provinceid=?";
        ResultSet resultSet = null;
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        try {
            ps.setInt(1,pid);
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                City city = new City(name,pid);
                list.add(city);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return list;
    }
}
