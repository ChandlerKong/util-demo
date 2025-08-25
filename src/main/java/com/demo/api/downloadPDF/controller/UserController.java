package com.demo.api.downloadPDF.controller;


import com.demo.api.downloadPDF.service.UserService;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 查询所有用户
    @GetMapping
    public List<User> list() {
        return userService.list1();
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    // 新增用户
    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    // 更新用户
    @PutMapping
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }
}