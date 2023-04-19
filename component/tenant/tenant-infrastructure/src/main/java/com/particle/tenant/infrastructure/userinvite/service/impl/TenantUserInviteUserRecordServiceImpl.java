package com.particle.tenant.infrastructure.userinvite.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteUserRecordDO;
import com.particle.tenant.infrastructure.userinvite.mapper.TenantUserInviteUserRecordMapper;
import com.particle.tenant.infrastructure.userinvite.service.ITenantUserInviteUserRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 租户用户邀请记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Component
public class TenantUserInviteUserRecordServiceImpl extends IBaseServiceImpl<TenantUserInviteUserRecordMapper, TenantUserInviteUserRecordDO> implements ITenantUserInviteUserRecordService {
	private IBaseQueryCommandMapStruct<TenantUserInviteUserRecordDO> queryCommandMapStruct;

	@Override
	protected TenantUserInviteUserRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantUserInviteUserRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantUserInviteUserRecordDO po) {
	}

	@Override
	protected void preUpdate(TenantUserInviteUserRecordDO po) {
    
	}
}
