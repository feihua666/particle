package com.particle.openplatform.infrastructure.openapi.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import com.particle.openplatform.infrastructure.openapi.mapper.OpenplatformOpenapiBatchQueryRecordMapper;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口批量查询记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Component
public class OpenplatformOpenapiBatchQueryRecordServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiBatchQueryRecordMapper, OpenplatformOpenapiBatchQueryRecordDO> implements IOpenplatformOpenapiBatchQueryRecordService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiBatchQueryRecordDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiBatchQueryRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiBatchQueryRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiBatchQueryRecordDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiBatchQueryRecordDO po) {

	}
}
