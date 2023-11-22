package com.particle.global.neo4j.dto.basic;

import com.particle.global.neo4j.repository.SnowflakeIdNeo4jGenerator;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

/**
 * <p>
 * 基础的图数据库带基础共用属性
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 18:21
 */
@Data
public class BaseGDO extends GDO{

    /**
     * 使用雪花算法生成id
     * neo4j 内容有一个属性 __internalNeo4jId__ 是其自动生成的id，所以如果自己再定义id默认会对应映射，
     * 如果添加{@link SnowflakeIdNeo4jGenerator} 自定义生成id，那么添加的节点就多了一个属性id，查询结果也是自己生成的id映射，其内部自动生成的id会被覆盖
     */
    @Id
    @GeneratedValue/*(value = SnowflakeIdNeo4jGenerator.class, generatorClass = SnowflakeIdNeo4jGenerator.class)*/
    private Long id;

}
