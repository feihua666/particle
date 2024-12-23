package com.particle.func.infrastructure.funcapplicationfuncrel.service.impl;

import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.mapper.FuncApplicationFuncRelMapper;
import com.particle.func.infrastructure.funcapplicationfuncrel.service.IFuncApplicationFuncRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 功能应用功能关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Component
public class FuncApplicationFuncRelServiceImpl extends IBaseServiceImpl<FuncApplicationFuncRelMapper, FuncApplicationFuncRelDO> implements IFuncApplicationFuncRelService {
	private IBaseQueryCommandMapStruct<FuncApplicationFuncRelDO> queryCommandMapStruct;

	@Override
	protected FuncApplicationFuncRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncApplicationFuncRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FuncApplicationFuncRelDO po) {
	}

	@Override
	protected void preUpdate(FuncApplicationFuncRelDO po) {

	}
}
