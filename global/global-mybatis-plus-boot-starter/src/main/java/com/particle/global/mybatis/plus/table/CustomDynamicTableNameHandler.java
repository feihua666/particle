package com.particle.global.mybatis.plus.table;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.particle.global.tool.thread.ThreadContextTool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 动态表名处理器
 * </p>
 *
 * @author yangwei
 * @since 2025/11/17 18:32
 */
public class CustomDynamicTableNameHandler implements TableNameHandler {

    private static final String dynamicTableNameKey = "dynamicTableName";
    @Override
    public String dynamicTableName(String sql, String tableName) {
        Object o = ThreadContextTool.get(dynamicTableNameKey);
        if (o != null) {
            Map<String,String> map = (Map<String,String>) o;
            String newTableName = map.get(tableName);
            if (newTableName != null) {
                return newTableName;
            }
        }
        return tableName;
    }


    /**
     * 设置需要替换的表名
     * @param oldTableName
     * @param newTableName
     */
    public static void setDynamicTableName(String oldTableName,String newTableName){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put(oldTableName, newTableName);
        ThreadContextTool.put(dynamicTableNameKey, stringStringHashMap);
    }
    public static void setDynamicTableName(Map<String,String> tableNameMap){
        ThreadContextTool.put(dynamicTableNameKey, tableNameMap);
    }
    /**
     * 清除动态表名
     */
    public static void clearDynamicTableName(){
        ThreadContextTool.remove(dynamicTableNameKey);
    }
}
