package com.particle.crm.infrastructure.relation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.crm.infrastructure.relation.mapper.CrmCustomerRelationMapper;
import com.particle.crm.infrastructure.relation.service.ICrmCustomerRelationService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 客户与客户关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Component
public class CrmCustomerRelationServiceImpl extends IBaseServiceImpl<CrmCustomerRelationMapper, CrmCustomerRelationDO> implements ICrmCustomerRelationService {
	private IBaseQueryCommandMapStruct<CrmCustomerRelationDO> queryCommandMapStruct;

	@Override
	protected CrmCustomerRelationDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCustomerRelationDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCustomerRelationDO po) {
	}

	@Override
	protected void preUpdate(CrmCustomerRelationDO po) {
    
	}
}
