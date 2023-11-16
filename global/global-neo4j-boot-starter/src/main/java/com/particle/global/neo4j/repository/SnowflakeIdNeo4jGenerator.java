package com.particle.global.neo4j.repository;

import com.particle.global.tool.id.SnowflakeIdTool;
import org.springframework.data.neo4j.core.schema.IdGenerator;

/**
 * <p>
 * 基于雪花算法的长整型id生成器
 * </p>
 *
 * @author yangwei
 * @since 2023/11/15 10:05
 */
public class SnowflakeIdNeo4jGenerator implements IdGenerator<Long> {
    @Override
    public Long generateId(String primaryLabel, Object entity) {
        return SnowflakeIdTool.nextId();
    }
}
