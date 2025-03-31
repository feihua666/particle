package com.particle.global.tool.datasource;

/**
 * <p>
 * 定义一个数据源服务，用于切换数据源
 * </p>
 *
 * @author yangwei
 * @since 2025/1/2 11:25
 */
public interface DatasourceService {

    void changeDatasource(String datasourceName);

    void restoreDatasource(String datasourceName);
}
