package com.particle.crm.infrastructure.customer.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import com.particle.crm.infrastructure.customer.mapper.CrmCustomerContactMapper;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerContactService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 客户联系方式 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Component
public class CrmCustomerContactServiceImpl extends IBaseServiceImpl<CrmCustomerContactMapper, CrmCustomerContactDO> implements ICrmCustomerContactService {
	private IBaseQueryCommandMapStruct<CrmCustomerContactDO> queryCommandMapStruct;

	@Override
	protected CrmCustomerContactDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCustomerContactDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCustomerContactDO po) {
	}

	@Override
	protected void preUpdate(CrmCustomerContactDO po) {
    
	}
}
