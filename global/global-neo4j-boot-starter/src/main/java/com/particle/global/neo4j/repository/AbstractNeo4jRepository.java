package com.particle.global.neo4j.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;

/**
 * <p>
 * 抽象实现，该类只是一个示例，表明可以使用neo4jClient进行引用，满足复杂需求
 * </p>
 *
 * @author yangwei
 * @since 2023/11/15 16:24
 */
public abstract class AbstractNeo4jRepository<T, ID> implements BaseNeo4jRepository<T, ID>{

    /**
     * 使用客户端查询复杂的查询
     */
    @Autowired
    protected Neo4jClient neo4jClient;

    @Autowired
    protected Neo4jTemplate neo4jTemplate;
}
