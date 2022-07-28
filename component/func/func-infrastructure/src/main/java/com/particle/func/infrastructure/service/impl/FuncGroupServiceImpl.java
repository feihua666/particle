package com.particle.func.infrastructure.service.impl;

import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.func.infrastructure.mapper.FuncGroupMapper;
import com.particle.func.infrastructure.service.IFuncGroupService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 功能组 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
public class FuncGroupServiceImpl extends IBaseServiceImpl<FuncGroupMapper, FuncGroupDO> implements IFuncGroupService {

	private IBaseQueryCommandMapStruct<FuncGroupDO> queryCommandMapStruct;

	@Override
	protected FuncGroupDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncGroupDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
