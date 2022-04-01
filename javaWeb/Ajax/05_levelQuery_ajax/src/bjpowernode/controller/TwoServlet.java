package bjpowernode.controller;

import bjpowernode.dao.CityDao;
import bjpowernode.entity.City;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TwoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        CityDao cityDao = new CityDao();

        List list = cityDao.queryByPid(Integer.valueOf(pid));

        Object obj = JSONArray.toJSON(list);

        request.setAttribute("key",obj.toString());

        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }
}
