package com.particle.openplatform.infrastructure.bill.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 开放平台应用开放接口月汇总 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper, OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> implements IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> queryCommandMapStruct;
	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper openplatformOpenapiRecordAppOpenapiMonthSummaryMapper;

	@Override
	protected OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO po) {
    
	}

	/**
	 * 开放平台应用开放接口月汇总 应用统计
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam
	 * @return
	 */
	public List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO> openAppIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam){
		return openplatformOpenapiRecordAppOpenapiMonthSummaryMapper.openAppIdStatistics(openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam);
	}

	/**
	 * 开放平台应用开放接口月汇总 客户统计
	 * @param openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam
	 * @return
	 */
	public List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO> customerIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam){
		return openplatformOpenapiRecordAppOpenapiMonthSummaryMapper.customerIdStatistics(openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam);
	}



	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper(OpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper openplatformOpenapiRecordAppOpenapiMonthSummaryMapper) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryMapper = openplatformOpenapiRecordAppOpenapiMonthSummaryMapper;
	}
}
