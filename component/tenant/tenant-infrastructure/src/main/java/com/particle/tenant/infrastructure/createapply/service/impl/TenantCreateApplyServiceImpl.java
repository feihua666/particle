package com.particle.tenant.infrastructure.createapply.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.tenant.infrastructure.createapply.mapper.TenantCreateApplyMapper;
import com.particle.tenant.infrastructure.createapply.service.ITenantCreateApplyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 租户创建申请 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Component
public class TenantCreateApplyServiceImpl extends IBaseServiceImpl<TenantCreateApplyMapper, TenantCreateApplyDO> implements ITenantCreateApplyService {
	private IBaseQueryCommandMapStruct<TenantCreateApplyDO> queryCommandMapStruct;

	@Override
	protected TenantCreateApplyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantCreateApplyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantCreateApplyDO po) {
	}

	@Override
	protected void preUpdate(TenantCreateApplyDO po) {
    
	}
}
