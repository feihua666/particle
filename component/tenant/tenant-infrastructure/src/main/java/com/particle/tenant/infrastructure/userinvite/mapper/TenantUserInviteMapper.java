package com.particle.tenant.infrastructure.userinvite.mapper;

import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户用户邀请 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Mapper
public interface TenantUserInviteMapper extends IBaseMapper<TenantUserInviteDO> {

}
