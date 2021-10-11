package com.longyu.base.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyLoginFilter implements Filter {

    private String[] ignoreArr = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ignoreArr = filterConfig.getInitParameter("ignore").split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        if(isIgnore(req)){
            filterChain.doFilter(req, resp);
        }else {
            Object token = session.getAttribute("token");
            if (token == null) {
                return;
            }
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法调用了.......");
    }

    public Boolean isIgnore(HttpServletRequest request){
        String path = request.getRequestURI();
        for(String ignore:ignoreArr){
            if(path.contains(ignore)){
                return true;
            }
        }
        return false;
    }
}
