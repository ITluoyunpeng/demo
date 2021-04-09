package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private IUserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        String sql="select * from user_info";
        List<User> query = jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUser_id(resultSet.getString("user_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_sex(resultSet.getString("user_sex"));
                return user;
            }
        });
        for (User user:
             query) {
            System.out.println(user.getUser_name());
        }
    }
    @Test
    void jpaTest(){
        List<User> all = userService.findAll();
        for (User user:all
             ) {
            System.out.println(user);
        }
        Page<User> all1 = userService.findAll(PageRequest.of(1,5));
        for (User user : all) {
            System.out.println(user);
        }
    }
    @Test
    public  void testRedis(){
        redisTemplate.opsForValue().set("name","lyp");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

}
