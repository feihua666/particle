package com.particle.tenant.infrastructure.tenantfuncapplication.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.tenant.infrastructure.tenantfuncapplication.mapper.TenantFuncApplicationMapper;
import com.particle.tenant.infrastructure.tenantfuncapplication.service.ITenantFuncApplicationService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 租户功能应用 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Component("tenantFuncAppServiceImpl")
public class TenantFuncApplicationServiceImpl extends IBaseServiceImpl<TenantFuncApplicationMapper, TenantFuncApplicationDO> implements ITenantFuncApplicationService {
	private IBaseQueryCommandMapStruct<TenantFuncApplicationDO> queryCommandMapStruct;

	@Override
	protected TenantFuncApplicationDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantFuncApplicationDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantFuncApplicationDO po) {
	}

	@Override
	protected void preUpdate(TenantFuncApplicationDO po) {
    
	}
}
