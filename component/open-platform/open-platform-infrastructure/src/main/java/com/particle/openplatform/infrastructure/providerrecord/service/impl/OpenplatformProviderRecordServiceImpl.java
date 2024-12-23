package com.particle.openplatform.infrastructure.providerrecord.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import com.particle.openplatform.infrastructure.providerrecord.mapper.OpenplatformProviderRecordMapper;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放平台开放接口供应商调用记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Component
public class OpenplatformProviderRecordServiceImpl extends IBaseServiceImpl<OpenplatformProviderRecordMapper, OpenplatformProviderRecordDO> implements IOpenplatformProviderRecordService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderRecordDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderRecordDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformProviderRecordDO po) {

	}
}
