package com.particle.componentadmin.infrastructure.service;

import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 组件 服务类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
public interface IAdminComponentService extends IBaseService<AdminComponentDO> {

    /**
     * 根据组件英文名称查询
     * @param code
     * @return
     */
    default AdminComponentDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<AdminComponentDO>lambdaQuery().eq(AdminComponentDO::getCode, code));
    }



    /**
     * 根据组件英文名称查询多个
     * @param codes
     * @return
     */
    default List<AdminComponentDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<AdminComponentDO>lambdaQuery().in(AdminComponentDO::getCode, codes));
    }
            











}
