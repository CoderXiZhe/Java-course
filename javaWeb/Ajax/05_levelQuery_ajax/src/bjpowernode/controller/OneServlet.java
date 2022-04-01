package bjpowernode.controller;

import bjpowernode.dao.ProvinceDao;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProvinceDao provinceDao = new ProvinceDao();

        List list = provinceDao.show();

        Object obj = JSONArray.toJSON(list);

        request.setAttribute("key",obj.toString());

        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }
}
