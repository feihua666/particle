package com.particle.crm.infrastructure.tag.service.impl;

import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.crm.infrastructure.tag.mapper.CrmCustomerTagRelMapper;
import com.particle.crm.infrastructure.tag.service.ICrmCustomerTagRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 客户标签关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Component
public class CrmCustomerTagRelServiceImpl extends IBaseServiceImpl<CrmCustomerTagRelMapper, CrmCustomerTagRelDO> implements ICrmCustomerTagRelService {
	private IBaseQueryCommandMapStruct<CrmCustomerTagRelDO> queryCommandMapStruct;

	@Override
	protected CrmCustomerTagRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCustomerTagRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCustomerTagRelDO po) {
	}

	@Override
	protected void preUpdate(CrmCustomerTagRelDO po) {

	}
}
