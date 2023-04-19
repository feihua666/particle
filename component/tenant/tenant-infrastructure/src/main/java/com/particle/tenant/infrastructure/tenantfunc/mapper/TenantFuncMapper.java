package com.particle.tenant.infrastructure.tenantfunc.mapper;

import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户功能菜单 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Mapper
public interface TenantFuncMapper extends IBaseMapper<TenantFuncDO> {

}
