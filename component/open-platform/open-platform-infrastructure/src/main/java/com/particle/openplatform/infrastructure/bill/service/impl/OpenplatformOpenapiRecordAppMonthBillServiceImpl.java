package com.particle.openplatform.infrastructure.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformOpenapiRecordAppMonthBillMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppMonthBillService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台应用月账单 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Component
public class OpenplatformOpenapiRecordAppMonthBillServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiRecordAppMonthBillMapper, OpenplatformOpenapiRecordAppMonthBillDO> implements IOpenplatformOpenapiRecordAppMonthBillService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppMonthBillDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiRecordAppMonthBillDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppMonthBillDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordAppMonthBillDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordAppMonthBillDO po) {
    
	}
}
