package com.particle.openplatform.infrastructure.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformOpenapiRecordAppOpenapiDaySummaryMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


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

	@Override
	protected OpenplatformOpenapiRecordAppOpenapiDaySummaryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO po) {
    
	}
}
