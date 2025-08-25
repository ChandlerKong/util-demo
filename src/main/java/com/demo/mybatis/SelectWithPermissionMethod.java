package com.demo.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class SelectWithPermissionMethod extends AbstractMethod {

    public SelectWithPermissionMethod() {
        super("selectWithPermission"); // 注入方法名
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass,
                                                 Class<?> modelClass,
                                                 TableInfo tableInfo) {
        // 自定义 SQL：自动加上 is_deleted=0 并支持动态 creator_id
        String sql = String.format(
            "SELECT * FROM %s WHERE (currentUserId IS NULL OR id = #{currentUserId})",
            tableInfo.getTableName()
        );

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addSelectMappedStatementForTable(mapperClass, this.methodName, sqlSource, tableInfo);
    }
}