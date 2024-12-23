package com.particle.global.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * <p>
 * 基础的基于Neo4jRepository的基础接口，所有访问图数据库建议优先建议对应实体的接口操作类
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 18:13
 */
public interface BaseNeo4jRepository<T, ID> extends Neo4jRepository<T, ID> {

}
