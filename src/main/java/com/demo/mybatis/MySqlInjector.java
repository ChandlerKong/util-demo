package com.demo.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        // 获取默认方法列表
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        // 注入自定义方法
        methodList.add(new MySelectById());
        return methodList;
    }
}