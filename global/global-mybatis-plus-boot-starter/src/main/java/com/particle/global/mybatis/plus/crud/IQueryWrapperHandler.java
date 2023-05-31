package com.particle.global.mybatis.plus.crud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.particle.global.dto.basic.QueryCommand;

/**
 * <p>
 * 对 queryWrapper 做一些额外处理
 * 仅针对 {@link IBaseServiceImpl#getQueryWrapper(com.particle.global.dto.basic.QueryCommand)}
 * </p>
 *
 * @author yangwei
 * @since 2020-11-22 11:00
 */
public interface IQueryWrapperHandler<DO> {

    /**
     * 对查询条件做一些额外处理
     * @param queryWrapper
     * @param queryForm
     */
    void handle(QueryWrapper<DO> queryWrapper, QueryCommand queryForm);
}
