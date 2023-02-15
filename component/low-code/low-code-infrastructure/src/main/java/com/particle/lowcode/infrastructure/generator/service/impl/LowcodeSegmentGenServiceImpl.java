package com.particle.lowcode.infrastructure.generator.service.impl;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentGenDO;
import com.particle.lowcode.infrastructure.generator.mapper.LowcodeSegmentGenMapper;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentGenService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 低代码生成 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Component
public class LowcodeSegmentGenServiceImpl extends IBaseServiceImpl<LowcodeSegmentGenMapper, LowcodeSegmentGenDO> implements ILowcodeSegmentGenService {
	private IBaseQueryCommandMapStruct<LowcodeSegmentGenDO> queryCommandMapStruct;

	@Override
	protected LowcodeSegmentGenDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<LowcodeSegmentGenDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
