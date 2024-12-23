package com.particle.dept.infrastructure.deptuserrel.service.impl;

import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.mapper.DeptUserRelMapper;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 部门用户关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Component
public class DeptUserRelServiceImpl extends IBaseServiceImpl<DeptUserRelMapper, DeptUserRelDO> implements IDeptUserRelService {
	private IBaseQueryCommandMapStruct<DeptUserRelDO> queryCommandMapStruct;

	@Override
	protected DeptUserRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DeptUserRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DeptUserRelDO po) {
	}

	@Override
	protected void preUpdate(DeptUserRelDO po) {

	}
}
