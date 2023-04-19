package com.particle.tenant.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.mapper.TenantUserMapper;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 租户用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
public class TenantUserServiceImpl extends IBaseServiceImpl<TenantUserMapper, TenantUserDO> implements ITenantUserService {
	private IBaseQueryCommandMapStruct<TenantUserDO> queryCommandMapStruct;

	@Override
	protected TenantUserDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantUserDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantUserDO po) {
	}

	@Override
	protected void preUpdate(TenantUserDO po) {
    
	}
}
