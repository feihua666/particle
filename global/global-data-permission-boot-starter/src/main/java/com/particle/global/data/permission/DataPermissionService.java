package com.particle.global.data.permission;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.particle.global.dto.dataconstraint.DataConstraintContext;

/**
 * <p>
 * 数据权限服务
 * 定义数据权限规范服务，主要处理数据范围约束，比如：用户只能看到自己创建的数据
 * 该服务通常被底层基础设施层实现，比如：mybatis plus 的 wrapper
 * 本服务目前仅支持mybatis plus 的 wrapper
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 14:05
 */
public interface DataPermissionService {
    /**
     * 数据权限操作行为
     */
    public static enum Action{
        /**
         * 修改
         */
        update,
        /**
         * 删除
         */
        delete,
        /**
         * 查询
         */
        query
    }

    default boolean support(Action action){
        return true;
    }
	/**
	 * 数据范围约束条件内容包装
     * 注意：如果不处理请原样返回 wrapper
	 * @param wrapper
	 */
	AbstractWrapper dataConstraint(AbstractWrapper wrapper, DataConstraintContext dataConstraintContext);
}
