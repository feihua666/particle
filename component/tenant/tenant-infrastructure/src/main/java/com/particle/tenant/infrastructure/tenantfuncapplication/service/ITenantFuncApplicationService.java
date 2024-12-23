package com.particle.tenant.infrastructure.tenantfuncapplication.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;

import java.util.List;

/**
 * <p>
 * 租户功能应用 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
public interface ITenantFuncApplicationService extends IBaseService<TenantFuncApplicationDO> {

    /**
     * 根据功能应用id查询
     * @param funcApplicationId
     * @return
     */
    default List<TenantFuncApplicationDO> getByFuncApplicationId(Long funcApplicationId) {
        Assert.notNull(funcApplicationId,"funcApplicationId 不能为空");
        return list(Wrappers.<TenantFuncApplicationDO>lambdaQuery().eq(TenantFuncApplicationDO::getFuncApplicationId, funcApplicationId));
    }



    /**
     * 根据功能应用id查询多个
     * @param funcApplicationIds
     * @return
     */
    default List<TenantFuncApplicationDO> getByFuncApplicationIds(List<Long> funcApplicationIds) {
        Assert.notEmpty(funcApplicationIds,"funcApplicationIds 不能为空");
        return list(Wrappers.<TenantFuncApplicationDO>lambdaQuery().in(TenantFuncApplicationDO::getFuncApplicationId, funcApplicationIds));
    }













}
