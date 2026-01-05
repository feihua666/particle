package com.particle.global.tool.datasource;

import com.particle.global.tool.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 数据源工具，本工具主要是用来切换数据源，主要结合 global-datasource-boot-starter 多数数据源场景使用
 * 在切换数据源是，应该始终使用该工具切换，保持充分解偶，不强制依赖多数据源，即使在不使用多数据源模块时，也可以正常工作而不修改代码
 * </p>
 *
 * @author yangwei
 * @since 2025/1/2 11:24
 */
@Slf4j
public class DatasourceTool {

    private static volatile DatasourceService datasourceService;

    /**
     * 切换数据源
     * @param datasourceName
     */
    public static void changeDatasource(String datasourceName){
        getDatasourceService().changeDatasource(datasourceName);
    }

    /**
     * 还原数据源
     * @param datasourceName
     */
    public static void restoreDatasource(String datasourceName){
        getDatasourceService().restoreDatasource(datasourceName);
    }

    /**
     * 初始化数据源服务
     */
    private static DatasourceService getDatasourceService(){
        if (datasourceService == null) {
            synchronized(DatasourceTool.class) {
                if (datasourceService == null) {
                    try {
                        datasourceService = SpringContextHolder.getBean(DatasourceService.class);
                    } catch (Exception e) {
                        log.warn("No bean named 'datasourceService' is defined, use empty instead");
                    }finally {
                        if (datasourceService == null) {
                            datasourceService = new DefaultEmptyDatasourceServiceImpl();
                        }
                    }
                }
            }
        }
        return datasourceService;
    }
}
