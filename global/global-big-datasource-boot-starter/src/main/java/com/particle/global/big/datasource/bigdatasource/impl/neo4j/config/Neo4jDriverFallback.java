package com.particle.global.big.datasource.bigdatasource.impl.neo4j.config;

import org.neo4j.driver.Driver;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;

/**
 * <p>
 * 如果driver构建失败，进行其他处理尝试创建
 * </p>
 *
 * @author yangwei
 * @since 2023/11/24 13:11
 */
public interface Neo4jDriverFallback {

    public Driver neo4jDriver(Neo4jProperties properties, Throwable throwable);
}
