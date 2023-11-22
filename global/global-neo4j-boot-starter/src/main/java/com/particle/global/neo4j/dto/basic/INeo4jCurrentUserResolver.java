package com.particle.global.neo4j.dto.basic;

/**
 * <p>
 * 用户在添加节点或更新节点时获取当前登录用户id，如果用户未登录，将返回{@link com.particle.global.mybatis.plus.fill.LoginUserIdResolver#DEFAULT_USER_ID}
 * </p>
 *
 * @author yangwei
 * @since 2023/11/22 16:35
 */
public interface INeo4jCurrentUserResolver {

    /**
     * 当前登录用户id
     * @return
     */
    Long currentUserId();

    /**
     * 当前的租户id
     * @return
     */
    Long currentTenantId();
}
