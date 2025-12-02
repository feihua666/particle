package com.particle.data.domain.dynamicdata.gateway;

import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 动态数据指标分类 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
public interface DynamicDataIndicatorCategoryGateway extends IBaseGateway<DynamicDataIndicatorCategoryId,DynamicDataIndicatorCategory> {

    void createTable(String tableName, String comment);
    void dropTable(String tableName);
}
