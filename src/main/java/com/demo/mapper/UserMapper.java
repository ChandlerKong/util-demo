package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.PostConstruct;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectWithPermission(@Param("currentUserId") Long currentUserId);
}