package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IUserService {
    Optional<User> findById(String id);
    List<User> findAll();
    User save(User user);
    void delete(String id);
    Page<User> findAll(Pageable pageable);//分页接口
}
