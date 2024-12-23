package com.particle.tenant.infrastructure.userinvite.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteUserRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户用户邀请记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Mapper
public interface TenantUserInviteUserRecordMapper extends IBaseMapper<TenantUserInviteUserRecordDO> {

}
