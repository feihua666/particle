package com.particle.lowcode.infrastructure.generator.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelDO;
import com.particle.lowcode.infrastructure.generator.mapper.LowcodeModelMapper;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码模型 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
public class LowcodeModelServiceImpl extends IBaseServiceImpl<LowcodeModelMapper, LowcodeModelDO> implements ILowcodeModelService {
	private IBaseQueryCommandMapStruct<LowcodeModelDO> queryCommandMapStruct;

	@Override
	protected LowcodeModelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<LowcodeModelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
