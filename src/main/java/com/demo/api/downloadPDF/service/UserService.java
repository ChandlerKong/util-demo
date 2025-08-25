package com.demo.api.downloadPDF.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    @Transactional
    public List<User> list1() {
        for (int i = 0; i < 30; i++){
            baseMapper.selectById(1L);
            baseMapper.selectById(2L);
            baseMapper.selectById(1L);
            baseMapper.selectById(2L);
        }
        return null;
    }

}