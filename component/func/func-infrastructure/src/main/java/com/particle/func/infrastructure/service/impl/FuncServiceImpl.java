package com.particle.func.infrastructure.service.impl;

import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.mapper.FuncMapper;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单功能 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
public class FuncServiceImpl extends IBaseServiceImpl<FuncMapper, FuncDO> implements IFuncService {

	private IBaseQueryCommandMapStruct<FuncDO> queryCommandMapStruct;

	@Override
	protected FuncDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
