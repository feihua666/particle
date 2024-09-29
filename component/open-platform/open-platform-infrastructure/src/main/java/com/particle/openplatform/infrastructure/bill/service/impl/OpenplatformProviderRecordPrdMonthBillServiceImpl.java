package com.particle.openplatform.infrastructure.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdMonthBillDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformProviderRecordPrdMonthBillMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdMonthBillService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台供应商月账单 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Component
public class OpenplatformProviderRecordPrdMonthBillServiceImpl extends IBaseServiceImpl<OpenplatformProviderRecordPrdMonthBillMapper, OpenplatformProviderRecordPrdMonthBillDO> implements IOpenplatformProviderRecordPrdMonthBillService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdMonthBillDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderRecordPrdMonthBillDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdMonthBillDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderRecordPrdMonthBillDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformProviderRecordPrdMonthBillDO po) {
    
	}
}
