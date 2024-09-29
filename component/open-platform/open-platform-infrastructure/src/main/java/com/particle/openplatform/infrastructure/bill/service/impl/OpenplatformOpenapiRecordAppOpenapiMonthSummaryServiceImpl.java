package com.particle.openplatform.infrastructure.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


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

	@Override
	protected OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO po) {
    
	}
}
