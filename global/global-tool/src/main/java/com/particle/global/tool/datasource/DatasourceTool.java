package com.particle.global.tool.datasource;

import com.particle.global.tool.spring.SpringContextHolder;

/**
 * <p>
 * 数据源工具，本工具主要是用来切换数据源，主要结合 global-datasource-boot-starter 多数数据源场景使用
 * </p>
 *
 * @author yangwei
 * @since 2025/1/2 11:24
 */
public class DatasourceTool {

    private static DatasourceService datasourceService;

    /**
     * 切换数据源
     * @param datasourceName
     */
    public static void changeDatasource(String datasourceName){
        initDatasourceService();
        datasourceService.changeDatasource(datasourceName);
    }

    /**
     * 还原数据源
     * @param datasourceName
     */
    public static void restoreDatasource(String datasourceName){
        initDatasourceService();
        datasourceService.restoreDatasource(datasourceName);
    }

    /**
     * 初始化数据源服务
     */
    private static void initDatasourceService(){
        if (datasourceService == null) {
            DatasourceService bean = null;
            try {
                bean = SpringContextHolder.getBean(DatasourceService.class);
                if (bean != null) {
                    datasourceService = bean;
                }else {
                    datasourceService = new DefaultEmptyDatasourceServiceImpl();
                }
            } catch (Exception e) {
                datasourceService = new DefaultEmptyDatasourceServiceImpl();
            }

        }
    }
}
