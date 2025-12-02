/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baomidou.mybatisplus.core.injector.methods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.particle.global.mybatis.plus.table.DynamicDO;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * 插入一条数据（选择字段插入）
 *
 * @author hubin
 * @since 2018-04-06
 */
public class Insert extends AbstractMethod {

    /**
     * 自增主键字段是否忽略
     *
     * @since 3.5.4
     */
    private boolean ignoreAutoIncrementColumn;

    public Insert() {
        this(SqlMethod.INSERT_ONE.getMethod());
    }

    /**
     * @param ignoreAutoIncrementColumn 是否忽略自增长主键字段
     * @since 3.5.4
     */
    public Insert(boolean ignoreAutoIncrementColumn) {
        this(SqlMethod.INSERT_ONE.getMethod());
        this.ignoreAutoIncrementColumn = ignoreAutoIncrementColumn;
    }


    /**
     * @param name 方法名
     * @since 3.5.0
     */
    public Insert(String name) {
        super(name);
    }

    /**
     * @param name                      方法名
     * @param ignoreAutoIncrementColumn 是否忽略自增长主键字段
     * @since 3.5.4
     */
    public Insert(String name, boolean ignoreAutoIncrementColumn) {
        super(name);
        this.ignoreAutoIncrementColumn = ignoreAutoIncrementColumn;
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = NoKeyGenerator.INSTANCE;
        SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
        // 修改这里将之前的注释了，重新添加了注入方法，以支持动态字段
        // String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(null, ignoreAutoIncrementColumn),
        //         LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        // String valuesScript = LEFT_BRACKET + NEWLINE + SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(null, ignoreAutoIncrementColumn),
        //         null, null, null, COMMA) + NEWLINE + RIGHT_BRACKET;
        String columnScript = SqlScriptUtils.convertTrim(getAllInsertSqlColumnMaybeIf(modelClass, tableInfo),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = LEFT_BRACKET + NEWLINE + SqlScriptUtils.convertTrim(getAllInsertSqlPropertyMaybeIf(modelClass, tableInfo),
                null, null, null, COMMA) + NEWLINE + RIGHT_BRACKET;
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotBlank(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /* 自增主键 */
                keyGenerator = Jdbc3KeyGenerator.INSTANCE;
                keyProperty = tableInfo.getKeyProperty();
                // 去除转义符
                keyColumn = SqlInjectionUtils.removeEscapeCharacter(tableInfo.getKeyColumn());
            } else if (null != tableInfo.getKeySequence()) {
                keyGenerator = TableInfoHelper.genKeyGenerator(methodName, tableInfo, builderAssistant);
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            }
        }
        // 表名使用动态设置的
        // String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
        String sql = String.format(sqlMethod.getSql(), modelClass.isAssignableFrom(DynamicDO.class)? "${tableName}" : tableInfo.getTableName(), columnScript, valuesScript);
        SqlSource sqlSource = super.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, methodName, sqlSource, keyGenerator, keyProperty, keyColumn);
    }

    /**
     * 自定义支持动态字段
     * @param modelClass
     * @param tableInfo
     * @return
     */
    private String getAllInsertSqlColumnMaybeIf(Class<?> modelClass,TableInfo tableInfo) {
        /**
         * 经调试原理的生成如下：
         * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
         * <trim prefix="(" suffix=")" suffixOverrides=",">
         *     id,
         *
         *     <if test="code != null">code,</if>
         *
         *     <if test="name != null">name,</if>
         *
         *     <if test="nameSimple != null">name_simple,</if>
         *
         *     <if test="spellFirst != null">spell_first,</if>
         *
         *     <if test="spellSimple != null">spell_simple,</if>
         *
         *     <if test="spell != null">spell,</if>
         *
         *     <if test="typeDictId != null">type_dict_id,</if>
         *
         *     <if test="longitude != null">longitude,</if>
         *
         *     <if test="latitude != null">latitude,</if>
         *
         *     <if test="remark != null">remark,</if>
         *
         *     <if test="seq != null">seq,</if>
         *     level,
         *
         *     <if test="parentId != null">parent_id,</if>
         *
         *     <if test="parentId1 != null">parent_id1,</if>
         *
         *     <if test="parentId2 != null">parent_id2,</if>
         *
         *     <if test="parentId3 != null">parent_id3,</if>
         *
         *     <if test="parentId4 != null">parent_id4,</if>
         *
         *     <if test="parentId5 != null">parent_id5,</if>
         *
         *     <if test="parentId6 != null">parent_id6,</if>
         *
         *     <if test="parentId7 != null">parent_id7,</if>
         *
         *     <if test="parentId8 != null">parent_id8,</if>
         *
         *     <if test="parentId9 != null">parent_id9,</if>
         *
         *     <if test="parentId10 != null">parent_id10,</if>
         *     create_at,
         * create_by,
         * update_at,
         *
         *     <if test="updateBy != null">update_by,</if>
         *     version,
         *
         *     <if test="tenantId != null">tenant_id,</if>
         *
         * </trim>
         */
        String origin = tableInfo.getAllInsertSqlColumnMaybeIf(null, ignoreAutoIncrementColumn);
        if (!modelClass.isAssignableFrom(DynamicDO.class)) {
            return origin;
        }
        String prefix = null;
        String keyInsertSqlColumn = tableInfo.getKeyInsertSqlColumn(false, prefix, true);

        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        final String newPrefix = prefix == null ? EMPTY : prefix;
        String fieldSqlColumn = fieldList.stream().map(i -> i.getInsertSqlColumnMaybeIf(newPrefix))
                .filter(Objects::nonNull).collect(joining(NEWLINE));

        // 仅注入以下字段
        String dynamicInsertSqlColumn = "<foreach collection='columnNames' item='column'>${column},</foreach>";
        return keyInsertSqlColumn + dynamicInsertSqlColumn + fieldSqlColumn;
    }

    /**
     * 自定义支持动态字段
     * @param modelClass
     * @param tableInfo
     * @return
     */
    private String getAllInsertSqlPropertyMaybeIf(Class<?> modelClass,TableInfo tableInfo) {
        /**
         * 经调试原来的生成如下：
         * (
         * <trim suffixOverrides=",">
         * #{id},
         * <if test="code != null">#{code},</if>
         * <if test="name != null">#{name},</if>
         * <if test="nameSimple != null">#{nameSimple},</if>
         * <if test="spellFirst != null">#{spellFirst},</if>
         * <if test="spellSimple != null">#{spellSimple},</if>
         * <if test="spell != null">#{spell},</if>
         * <if test="typeDictId != null">#{typeDictId},</if>
         * <if test="longitude != null">#{longitude},</if>
         * <if test="latitude != null">#{latitude},</if>
         * <if test="remark != null">#{remark},</if>
         * <if test="seq != null">#{seq},</if>
         * #{level},
         * <if test="parentId != null">#{parentId},</if>
         * <if test="parentId1 != null">#{parentId1},</if>
         * <if test="parentId2 != null">#{parentId2},</if>
         * <if test="parentId3 != null">#{parentId3},</if>
         * <if test="parentId4 != null">#{parentId4},</if>
         * <if test="parentId5 != null">#{parentId5},</if>
         * <if test="parentId6 != null">#{parentId6},</if>
         * <if test="parentId7 != null">#{parentId7},</if>
         * <if test="parentId8 != null">#{parentId8},</if>
         * <if test="parentId9 != null">#{parentId9},</if>
         * <if test="parentId10 != null">#{parentId10},</if>
         * #{createAt},
         * #{createBy},
         * #{updateAt},
         * <if test="updateBy != null">#{updateBy},</if>
         * #{version},
         * <if test="tenantId != null">#{tenantId},</if>
         * </trim>
         * )
         */
        String origin = tableInfo.getAllInsertSqlPropertyMaybeIf(null, ignoreAutoIncrementColumn);
        if (!modelClass.isAssignableFrom(DynamicDO.class)) {
            return origin;
        }
        String prefix = null;
        final String newPrefix = prefix == null ? EMPTY : prefix;
        String keyInsertSqlProperty = tableInfo.getKeyInsertSqlProperty(false, newPrefix, true);

        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        String fieldSqlProperty = fieldList.stream()
                .map(i -> i.getInsertSqlPropertyMaybeIf(newPrefix)).filter(Objects::nonNull).collect(joining(NEWLINE));

        // 仅注入以下字段
        String dynamicInsertSqlProperty = "<foreach collection='columnNames' item='column'>#{data.${column}},</foreach>";
        return keyInsertSqlProperty + dynamicInsertSqlProperty + fieldSqlProperty;
    }
}
