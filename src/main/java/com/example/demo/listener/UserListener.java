package com.example.demo.listener;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
@WebListener
public class UserListener implements ServletContextListener {
    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletCOntext上下文初始化。。。");
        ServletContext servletContext = sce.getServletContext();
        servletContext.set



                6
        //List<User> userList = userService.findAll();
        //System.out.println(userList+">>>");
        redisTemplate.opsForValue().set("name","luoyunpeng");
       // List<User> allUser = redisTemplate.opsForList().range("allUser", 0, -1);
        //assert allUser != null;
       // System.out.println("现在一共有："+allUser.size()+"个用户");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletCOntext上下文销毁。。。");
    }
}
