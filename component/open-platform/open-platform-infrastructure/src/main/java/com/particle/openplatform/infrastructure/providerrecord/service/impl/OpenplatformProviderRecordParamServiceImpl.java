package com.particle.openplatform.infrastructure.providerrecord.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import com.particle.openplatform.infrastructure.providerrecord.mapper.OpenplatformProviderRecordParamMapper;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Component
public class OpenplatformProviderRecordParamServiceImpl extends IBaseServiceImpl<OpenplatformProviderRecordParamMapper, OpenplatformProviderRecordParamDO> implements IOpenplatformProviderRecordParamService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderRecordParamDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderRecordParamDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderRecordParamDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderRecordParamDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformProviderRecordParamDO po) {

	}
}
