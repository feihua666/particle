package com.particle.data.domain.dynamicdata.gateway;

import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 动态数据指标 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
public interface DynamicDataIndicatorGateway extends IBaseGateway<DynamicDataIndicatorId,DynamicDataIndicator> {
    /**
     * 添加字段
     * @param tableName
     * @param columnName
     * @param columnType
     */
    void addColumn(String tableName, String columnName, String columnType,boolean isRequired, String defaultValue, String comment);
    /**
     * 删除字段
     * @param tableName
     * @param columnName
     */
    void dropColumn(String tableName, String columnName);

    /**
     * 判断字段是否存在
     * @param tableName
     * @param columnName
     * @return
     */
    boolean isExistColumn(String tableName, String columnName);

}
