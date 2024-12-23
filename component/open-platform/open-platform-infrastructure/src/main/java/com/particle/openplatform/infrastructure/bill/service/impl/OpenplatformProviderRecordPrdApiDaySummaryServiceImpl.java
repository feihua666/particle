package com.particle.openplatform.infrastructure.bill.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformProviderRecordPrdApiDaySummaryMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiDaySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放平台供应商接口日汇总 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Component
public class OpenplatformProviderRecordPrdApiDaySummaryServiceImpl extends IBaseServiceImpl<OpenplatformProviderRecordPrdApiDaySummaryMapper, OpenplatformProviderRecordPrdApiDaySummaryDO> implements IOpenplatformProviderRecordPrdApiDaySummaryService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdApiDaySummaryDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderRecordPrdApiDaySummaryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdApiDaySummaryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderRecordPrdApiDaySummaryDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformProviderRecordPrdApiDaySummaryDO po) {

	}
}
