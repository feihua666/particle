package com.particle.tenant.infrastructure.mapper;

import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户用户 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Mapper
public interface TenantUserMapper extends IBaseMapper<TenantUserDO> {

}
