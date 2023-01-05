package com.particle.lowcode.infrastructure.generator.service.impl;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.infrastructure.generator.mapper.LowcodeModelItemMapper;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelItemService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 低代码模型项目 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
public class LowcodeModelItemServiceImpl extends IBaseServiceImpl<LowcodeModelItemMapper, LowcodeModelItemDO> implements ILowcodeModelItemService {
	private IBaseQueryCommandMapStruct<LowcodeModelItemDO> queryCommandMapStruct;

	@Override
	protected LowcodeModelItemDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<LowcodeModelItemDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
