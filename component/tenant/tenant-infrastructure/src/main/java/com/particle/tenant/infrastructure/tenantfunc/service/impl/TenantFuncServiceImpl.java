package com.particle.tenant.infrastructure.tenantfunc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.mapper.TenantFuncMapper;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 租户功能菜单 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Component
public class TenantFuncServiceImpl extends IBaseServiceImpl<TenantFuncMapper, TenantFuncDO> implements ITenantFuncService {
	private IBaseQueryCommandMapStruct<TenantFuncDO> queryCommandMapStruct;

	@Override
	protected TenantFuncDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantFuncDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantFuncDO po) {
	}

	@Override
	protected void preUpdate(TenantFuncDO po) {

	}
}
