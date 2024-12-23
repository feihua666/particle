package com.particle.openplatform.infrastructure.bill.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformOpenapiRecordAppOpenapiDaySummaryMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 开放平台应用开放接口日汇总 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiRecordAppOpenapiDaySummaryMapper, OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> implements IOpenplatformOpenapiRecordAppOpenapiDaySummaryService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> queryCommandMapStruct;

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryMapper openplatformOpenapiRecordAppOpenapiDaySummaryMapper;
	@Override
	protected OpenplatformOpenapiRecordAppOpenapiDaySummaryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO po) {

	}

	@Override
	public List<OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics(OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam) {
		return openplatformOpenapiRecordAppOpenapiDaySummaryMapper.openAppIdOpenapiIdStatistics(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam);
	}

	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryMapper(OpenplatformOpenapiRecordAppOpenapiDaySummaryMapper openplatformOpenapiRecordAppOpenapiDaySummaryMapper) {
		this.openplatformOpenapiRecordAppOpenapiDaySummaryMapper = openplatformOpenapiRecordAppOpenapiDaySummaryMapper;
	}
}
