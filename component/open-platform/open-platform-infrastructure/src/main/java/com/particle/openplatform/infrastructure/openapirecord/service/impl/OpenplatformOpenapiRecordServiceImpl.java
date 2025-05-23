package com.particle.openplatform.infrastructure.openapirecord.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.infrastructure.openapirecord.dos.view.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.openapirecord.dto.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.openapirecord.mapper.OpenplatformOpenapiRecordMapper;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 开放平台开放接口调用记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Component
public class OpenplatformOpenapiRecordServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiRecordMapper, OpenplatformOpenapiRecordDO> implements IOpenplatformOpenapiRecordService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordDO> queryCommandMapStruct;

	private OpenplatformOpenapiRecordMapper openplatformOpenapiRecordMapper;
	@Override
	protected OpenplatformOpenapiRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordDO po) {

	}

	@Override
	public List<OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics(OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam) {
		return openplatformOpenapiRecordMapper.openAppIdOpenapiIdStatistics(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam);
	}

	@Autowired
	public void setOpenplatformOpenapiRecordMapper(OpenplatformOpenapiRecordMapper openplatformOpenapiRecordMapper) {
		this.openplatformOpenapiRecordMapper = openplatformOpenapiRecordMapper;
	}
}
