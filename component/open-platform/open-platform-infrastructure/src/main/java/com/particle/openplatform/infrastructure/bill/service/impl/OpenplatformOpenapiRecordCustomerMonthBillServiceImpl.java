package com.particle.openplatform.infrastructure.bill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import com.particle.openplatform.infrastructure.bill.mapper.OpenplatformOpenapiRecordCustomerMonthBillMapper;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordCustomerMonthBillService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台客户月账单 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Component
public class OpenplatformOpenapiRecordCustomerMonthBillServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiRecordCustomerMonthBillMapper, OpenplatformOpenapiRecordCustomerMonthBillDO> implements IOpenplatformOpenapiRecordCustomerMonthBillService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordCustomerMonthBillDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiRecordCustomerMonthBillDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordCustomerMonthBillDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiRecordCustomerMonthBillDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiRecordCustomerMonthBillDO po) {
    
	}
}
