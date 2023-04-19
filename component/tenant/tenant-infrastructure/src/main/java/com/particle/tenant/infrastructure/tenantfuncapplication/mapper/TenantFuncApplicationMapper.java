package com.particle.tenant.infrastructure.tenantfuncapplication.mapper;

import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户功能应用 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Mapper
public interface TenantFuncApplicationMapper extends IBaseMapper<TenantFuncApplicationDO> {

}
