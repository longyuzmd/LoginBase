package com.longyu.base;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class MyHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get方法调用");

        // 获取完整的请求路径
        String requestURL = req.getRequestURL().toString();
        System.out.println(requestURL);
        // 获取相对项目名的路径，资源路径
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        // 获取请求时的参数字符串
        String queryString = req.getQueryString();
        System.out.println(queryString);
        // 获取请求方式
        String method = req.getMethod();
        System.out.println(method);
        // 获取当前协议的版本
        String protocol = req.getProtocol();
        System.out.println(protocol);
        // 获取项目的站点名
        String contextPath = req.getContextPath();
        System.out.println(contextPath);


        /**
         * 响应的流的方式
         *  resp.getWriter() 字符流
         *
         *  resp.getOutputStream() 字节流
         *
         *  考虑乱码的情况：需要设置服务端和客户端的编码格式一致
         */
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type","text/html;charset=UTF-8");

        // 字符流
        PrintWriter writer = resp.getWriter();
        writer.write("<h2>欢迎!</h2>");

        // 字节流
//        ServletOutputStream out = resp.getOutputStream();
//        out.write("<h2>你好</h2>".getBytes());

    }

    /***
     * request 常用方法：
     * request.getRequestURL() 获取完整请求路径
     * request.getRequestURI() 相对项目的路径
     * request.get
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post方法调用");
        // 解决默认编码ISO-8859-1 乱码问题
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username.equals("zmd") && password.equals("123456")){
            HttpSession session = req.getSession();
            session.setAttribute("token","zmd123456");

            // 设置服务端的编码格式，响应数据
            resp.setCharacterEncoding("UTF-8");
            // 设置客户端编码
            resp.setHeader("content-type","text/html;charset=UTF-8");

            req.getRequestDispatcher("model.jsp").forward(req,resp);
        }else{
            return;
        }


    }

    /**
     * 不判断强求的类型，到底是post还是get,
     * 只要请求路径正确就调用
     */
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("========================");
//        doPost(req,resp);
//    }


}
