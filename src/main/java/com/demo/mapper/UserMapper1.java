package com.demo.mapper;

import com.demo.entity.User;
import com.demo.mybatis.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper1 extends MyBaseMapper<User> {
}