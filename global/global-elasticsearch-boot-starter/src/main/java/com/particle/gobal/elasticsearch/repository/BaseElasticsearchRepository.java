package com.particle.gobal.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 * 基础的基于ElasticsearchRepository的基础接口，所有访问es建议优先建议对应实体的接口操作类
 * </p>
 *
 * @author yangwei
 * @since 2023-12-07 13:15:38
 */
public interface BaseElasticsearchRepository<T, ID> extends ElasticsearchRepository<T, ID> {

}
