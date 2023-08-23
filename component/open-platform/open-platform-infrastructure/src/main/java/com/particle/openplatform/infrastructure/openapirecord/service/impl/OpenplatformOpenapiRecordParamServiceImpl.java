package com.particle.openplatform.infrastructure.openapirecord.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;
import com.particle.openplatform.infrastructure.openapirecord.mapper.OpenplatformOpenapiRecordParamMapper;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordParamService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台开放接口调用记录参数 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Component
public class OpenplatformOpenapiRecordParamServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiRecordParamMapper, OpenplatformOpenapiRecordParamDO> implements IOpenplatformOpenapiRecordParamService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordParamDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiRecordParamDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordParamDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordParamDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordParamDO po) {
    
	}
}
