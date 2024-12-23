package com.particle.openplatform.infrastructure.openapi.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiFeeDO;
import com.particle.openplatform.infrastructure.openapi.mapper.OpenplatformOpenapiFeeMapper;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放平台开放接口费用 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Component
public class OpenplatformOpenapiFeeServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiFeeMapper, OpenplatformOpenapiFeeDO> implements IOpenplatformOpenapiFeeService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiFeeDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiFeeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiFeeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiFeeDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiFeeDO po) {

	}
}
