package com.demo.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

public interface MyBaseMapper<T> extends BaseMapper<T> {
    T mySelectById(Serializable id);
}