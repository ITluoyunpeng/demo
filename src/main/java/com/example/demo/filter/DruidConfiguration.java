package com.example.demo.filter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class DruidConfiguration {

    @Value("${spring.datasource.loginUsername}")
    private String loginUsername;

    @Value("${spring.datasource.loginPassword}")
    private String loginPassword;

    /**
     * 配置Druid监控
     *
     * @param :
     * @return: org.springframework.boot.web.servlet.ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        // 注册服务
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
        // 设置控制台登录的用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 配置web监控的filter
     *
     * @param :
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        Map<String, String> initParams = new HashMap<>(1);
        // 设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "");
        filterRegistrationBean.addInitParameter("aopPatterns", "com.example.demo.service");
        // 验证所有请求
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
