package com.example.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class UserListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletCOntext上下文初始化。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletCOntext上下文销毁。。。");
    }
}
