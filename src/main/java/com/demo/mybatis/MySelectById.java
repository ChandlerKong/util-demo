package com.demo.mybatis;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

public class MySelectById extends AbstractMethod {

    protected MySelectById() {
        super("mySelectById");
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        final SqlMethod sqlMethod = SqlMethod.SELECT_BY_ID;
        final SqlSource sqlSource = new RawSqlSource(configuration, String.format(sqlMethod.getSql()
                , sqlSelectColumns(tableInfo, false)
                , tableInfo.getTableName()
                , tableInfo.getKeyColumn()
                , tableInfo.getKeyProperty()
                , " FOR UPDATE "), Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, "mySelectById", sqlSource, tableInfo);
    }
}