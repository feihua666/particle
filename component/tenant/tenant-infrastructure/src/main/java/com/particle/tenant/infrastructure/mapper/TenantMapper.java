package com.particle.tenant.infrastructure.mapper;

import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Mapper
public interface TenantMapper extends IBaseMapper<TenantDO> {

}
