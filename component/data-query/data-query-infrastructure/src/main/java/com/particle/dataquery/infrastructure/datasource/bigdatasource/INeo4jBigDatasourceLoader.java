package com.particle.dataquery.infrastructure.datasource.bigdatasource;

import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.Neo4jBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.config.Neo4jBigDatasourceConfig;

/**
 * <p>
 * 自定义一个neo4j数据源扩展接口，以支持自定义加载自己的数据源
 * </p>
 *
 * @author yangwei
 * @since 2023/11/28 10:24
 */
public interface INeo4jBigDatasourceLoader {

    /**
     * 加载自定义的数据源，该接口方法是一个扩展，可以实现该接口以在加载数据源是实现自定义neo4j数据源
     * @param name
     * @param type
     * @param neo4jBigDatasourceConfig
     * @return
     */
    Neo4jBigDatasource load(String name, BigDatasourceType type, Neo4jBigDatasourceConfig neo4jBigDatasourceConfig);
}
