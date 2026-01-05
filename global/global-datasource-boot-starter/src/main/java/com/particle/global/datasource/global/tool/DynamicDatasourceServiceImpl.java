package com.particle.global.datasource.global.tool;


import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.particle.global.tool.datasource.DatasourceService;

/**
 * <p>
 * 实现真正的数据库切换服务
 * </p>
 *
 * @author yangwei
 * @since 2025/1/2 11:26
 */
public class DynamicDatasourceServiceImpl implements DatasourceService {
    @Override
    public void changeDatasource(String datasourceName) {
        DynamicDataSourceContextHolder.push(datasourceName);
    }

    @Override
    public void restoreDatasource(String datasourceName) {
        DynamicDataSourceContextHolder.poll();
    }
}
