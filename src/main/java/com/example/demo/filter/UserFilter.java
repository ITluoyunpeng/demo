package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/user/userList")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("userFilter初始化。。。。。。");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String localAddr = request.getLocalAddr();
        System.out.println(localAddr+"doFilter....");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("userFilter销毁。。。。。。");
    }
}
