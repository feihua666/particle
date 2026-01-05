package com.particle.componentadmin.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.componentadmin.infrastructure.mapper.AdminComponentDependencyMapper;
import com.particle.componentadmin.infrastructure.service.IAdminComponentDependencyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 组件依赖关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Component
public class AdminComponentDependencyServiceImpl extends IBaseServiceImpl<AdminComponentDependencyMapper, AdminComponentDependencyDO> implements IAdminComponentDependencyService {
	private IBaseQueryCommandMapStruct<AdminComponentDependencyDO> queryCommandMapStruct;

	@Override
	protected AdminComponentDependencyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AdminComponentDependencyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AdminComponentDependencyDO po) {
	}

	@Override
	protected void preUpdate(AdminComponentDependencyDO po) {
    
	}
}
