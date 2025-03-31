package com.particle.global.spring.global.tool;


import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.particle.global.tool.datasource.DatasourceService;

/**
 * <p>
 * 默认的啥也不做的数据源切换服务实现
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
