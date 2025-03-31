package com.particle.global.tool.datasource;


/**
 * <p>
 * 默认的啥也不做的数据源切换服务实现
 * </p>
 *
 * @author yangwei
 * @since 2025/1/2 11:26
 */
public class DefaultEmptyDatasourceServiceImpl implements DatasourceService {
    @Override
    public void changeDatasource(String datasourceName) {

    }

    @Override
    public void restoreDatasource(String datasourceName) {

    }
}
