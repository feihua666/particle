package com.particle.dept.infrastructure.depttreeuserrel.service.impl;

import com.particle.dept.infrastructure.depttreeuserrel.dos.DeptTreeUserRelDO;
import com.particle.dept.infrastructure.depttreeuserrel.mapper.DeptTreeUserRelMapper;
import com.particle.dept.infrastructure.depttreeuserrel.service.IDeptTreeUserRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 部门树用户关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Component
public class DeptTreeUserRelServiceImpl extends IBaseServiceImpl<DeptTreeUserRelMapper, DeptTreeUserRelDO> implements IDeptTreeUserRelService {
	private IBaseQueryCommandMapStruct<DeptTreeUserRelDO> queryCommandMapStruct;

	@Override
	protected DeptTreeUserRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DeptTreeUserRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DeptTreeUserRelDO po) {
	}

	@Override
	protected void preUpdate(DeptTreeUserRelDO po) {

	}
}
