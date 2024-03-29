package com.particle.component.light.share.dict;


import com.google.common.collect.Maps;
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 字段类型 字典项
 * </p>
 *
 * @author yw
 * @since 2024-03-29 11:47:14
 */
public enum FieldType implements IDictItem {

    /**
     * object
     */
    object
    ,
    /**
     * array
     */
    array
    ,
    /**
     * string
     */
    string
    ,
    /**
     * date
     */
    date
    ,
    /**
     * datetime
     */
    datetime
    ,
    /**
     * time
     */
    time
    ,
    /**
     * number
     */
    number
    ,
    /**
     * float
     */
    FLOAT("float")
    ,
    /**
     * boolean
     */
    BOOLEAN("boolean")
    ;
    FieldType(){}
    FieldType(String name) {
        this.name = name;
    }

    private String name;
    @Override
    public String itemValue() {
        if (this.name != null) {
            return this.name;
        }
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.field_type.groupCode();
    }

    /**
     * 字段类型 字典组
     */
    public enum Group implements IDictGroup {
        field_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }

    private static Map<FieldType, Predicate<Object>> parseMap;
    static {
        parseMap = new TreeMap<>();
        // 注意key顺序很重要
        parseMap.put(FieldType.string, (o) -> o instanceof String || o instanceof Character);
        parseMap.put(FieldType.datetime, (o) -> o instanceof Date || o instanceof LocalDateTime);
        parseMap.put(FieldType.date, (o) -> o instanceof Date || o instanceof LocalDate);
        parseMap.put(FieldType.time, (o) -> o instanceof LocalTime);
        parseMap.put(FieldType.number, (o) -> o instanceof Integer || o instanceof Short || o instanceof Long);
        parseMap.put(FieldType.FLOAT, (o) -> o instanceof Float || o instanceof Double || o instanceof BigDecimal);
        parseMap.put(FieldType.BOOLEAN, (o) -> o instanceof Boolean);
        parseMap.put(FieldType.array, (o) -> o instanceof Collection);
        parseMap.put(FieldType.object, (o) -> {
            List<FieldType> collect = Arrays.stream(FieldType.values()).filter(fieldType -> fieldType != FieldType.object).collect(Collectors.toList());
            for (FieldType fieldType : collect) {

                if (parseMap.get(fieldType).test(o)) {
                    return false;
                }
            }
            return true;
        });
    }

    /**
     * 根据任意对象解析出类型
     * @param object
     * @return
     */
    public static FieldType parse(Object object) {
        if (object == null) {
            return null;
        }
        for (FieldType fieldType : parseMap.keySet()) {
            if (parseMap.get(fieldType).test(object)) {
                return fieldType;
            }
        }
        return null;
    }
}

