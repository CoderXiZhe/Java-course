package com.bjpowernode.crm.web.filter;


import com.bjpowernode.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到登录验证过滤器");
        //进行前置转换    父亲转儿子要强制他(上转下)
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //不能拦截默认欢迎资源文件和登录页面   要自动放行
        //System.out.println("URI:" + request.getRequestURI());
        //System.out.println("URL:" + request.getRequestURL());
        //System.out.println("contextpath:" + request.getContextPath());
        //System.out.println("servletpath:" + request.getServletPath());
        String path = request.getServletPath();
        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            chain.doFilter(req,res);
        }else{
            HttpSession session = request.getSession();//获取session域
            User user = (User) session.getAttribute("user");//强制转换 因为拿出来的是object对象


            if(user != null) {
                //user不空说明已经登录过了  直接放行
                chain.doFilter(req,res);

            }else{
                //没有登录过
                //重定向到登录页
            /*
            重定向的路径怎么写?
                在实际项目开发中，对于路径的使用，不论操作的是前端还是后端，
                应该一律使用【绝对路径】
            关于转发和重定向的路径的写法如下:
            转发:
                使用的是一种特殊的绝对路径的使用方式，这种绝对路径前面不加/项目名，这种路径也称之为内部路径
                    /login.jsp
            重定向:
                 使用的是传统绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径
                 /crm/login.jsp
            为什么使用重定向，使用转发不行吗?
                 转发之后，路径会停留在老路径上，而不是跳转之后最新资源的路径
                 我们应该在为用户跳转到登录页的同时，将浏览器的地址栏应该自动设置为当前的登录页的路径

            */
                //request.getRequestDispatcher("/login.jsp").forward(req,res);
                //System.out.println(request.getContextPath());
                response.sendRedirect(request.getContextPath() +"/login.jsp");
            }
        }



    }
}
