package com.particle.role.infrastructure.rolefuncrel.service.impl;

import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.mapper.RoleFuncRelMapper;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色菜单功能关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class RoleFuncRelServiceImpl extends IBaseServiceImpl<RoleFuncRelMapper, RoleFuncRelDO> implements IRoleFuncRelService {
	private IBaseQueryCommandMapStruct<RoleFuncRelDO> queryCommandMapStruct;

	@Override
	protected RoleFuncRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<RoleFuncRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
