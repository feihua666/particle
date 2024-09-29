package com.particle.openplatform.infrastructure.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformProviderRecordPrdApiMonthSummaryMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiMonthSummaryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台供应商接口月汇总 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Component
public class OpenplatformProviderRecordPrdApiMonthSummaryServiceImpl extends IBaseServiceImpl<OpenplatformProviderRecordPrdApiMonthSummaryMapper, OpenplatformProviderRecordPrdApiMonthSummaryDO> implements IOpenplatformProviderRecordPrdApiMonthSummaryService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdApiMonthSummaryDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderRecordPrdApiMonthSummaryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdApiMonthSummaryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderRecordPrdApiMonthSummaryDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformProviderRecordPrdApiMonthSummaryDO po) {
    
	}
}
