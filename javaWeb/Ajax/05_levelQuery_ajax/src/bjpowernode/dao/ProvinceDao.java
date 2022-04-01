package bjpowernode.dao;

import bjpowernode.entity.Province;
import bjpowernode.util.JdbcUtil;

import javax.print.attribute.standard.PresentationDirection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDao {
    public List show() {
        List list  = new ArrayList();
        String sql="select * from province";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        try {
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt(  "id");
                String name = resultSet.getString("name");
                Province province = new Province(id,name);
                list.add(province);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return list;
    }
}
