package com.particle.dataquery.domain.datasource.gateway.bigdatasource;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 数据查询中的数据源，映射到大数据源相关操作网关
 * </p>
 *
 * @author yangwei
 * @since 2025/5/13 10:48
 */
public interface DataQueryDatasourceDynamicBigDatasourceGateway extends IGateway {

    /**
     * 重新加载数据源
     * 添加目的是在http数据源上可以修改一些配置，如账号密码等，需要重新加载
     * @param dataQueryDatasourceId
     * @param removeOnly 仅移除
     * @return
     */
    boolean reloadDataQueryDatasource(Long dataQueryDatasourceId, boolean removeOnly);
}
