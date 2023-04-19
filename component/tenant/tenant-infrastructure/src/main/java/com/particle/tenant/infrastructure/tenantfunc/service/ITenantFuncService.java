package com.particle.tenant.infrastructure.tenantfunc.service;

import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 租户功能菜单 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
public interface ITenantFuncService extends IBaseService<TenantFuncDO> {

    /**
     * 根据用户id查询
     * @param funcId
     * @return
     */
    default List<TenantFuncDO> getByFuncId(Long funcId) {
        Assert.notNull(funcId,"funcId 不能为空");
        return list(Wrappers.<TenantFuncDO>lambdaQuery().eq(TenantFuncDO::getFuncId, funcId));
    }



    /**
     * 根据用户id查询多个
     * @param funcIds
     * @return
     */
    default List<TenantFuncDO> getByFuncIds(List<Long> funcIds) {
        Assert.notEmpty(funcIds,"funcIds 不能为空");
        return list(Wrappers.<TenantFuncDO>lambdaQuery().in(TenantFuncDO::getFuncId, funcIds));
    }
            









}
