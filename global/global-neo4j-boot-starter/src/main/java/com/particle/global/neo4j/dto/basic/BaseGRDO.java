package com.particle.global.neo4j.dto.basic;

import org.springframework.data.annotation.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

import java.time.LocalDateTime;

/**
 * <p>
 * 基础的图数据库带基础共用属性，主要用于关系实体
 * 一般子类上需要标{@link RelationshipProperties} 以代表关系属性
 * </p>
 *
 * @author yangwei
 * @since 2023/11/15 18:06
 */
public class BaseGRDO extends GDO{

    @RelationshipId
    @GeneratedValue
    private Long id;

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    @CreatedBy
    private Long createBy;

    /**
     * 修改时间
     */
    @LastModifiedDate
    private LocalDateTime updateAt;

    /**
     * 修改人
     */
    @LastModifiedBy
    private Long updateBy;

    /**
     * 乐观锁字段
     */
    @Version
    private Long version;

    /**
     * 租户id
     */
    private Long tenantId;
}
