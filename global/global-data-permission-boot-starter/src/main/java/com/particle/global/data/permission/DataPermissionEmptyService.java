package com.particle.global.data.permission;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.particle.global.dto.dataconstraint.DataConstraintContext;

/**
 * <p>
 * 定义一个空的实现，用于实现数据权限服务，如果数据权限服务不存在，则使用这个实现
 * </p>
 *
 * @author yangwei
 * @since 2025/12/26 10:46
 */
public class DataPermissionEmptyService implements DataPermissionService{
    @Override
    public AbstractWrapper dataConstraint(AbstractWrapper wrapper, DataConstraintContext dataConstraintContext) {
        return wrapper;
    }
}
