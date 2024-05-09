package com.particle.crm.infrastructure.customer.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.crm.infrastructure.customer.mapper.CrmCustomerMapper;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 客户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Component
public class CrmCustomerServiceImpl extends IBaseServiceImpl<CrmCustomerMapper, CrmCustomerDO> implements ICrmCustomerService {
	private IBaseQueryCommandMapStruct<CrmCustomerDO> queryCommandMapStruct;

	@Override
	protected CrmCustomerDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCustomerDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCustomerDO po) {
	    // 客户编码 已存在不能添加
	    if (StrUtil.isNotEmpty(po.getCode())) {
	    	    assertByColumn(po.getCode(),CrmCustomerDO::getCode,false);
	    }
	}

	@Override
	protected void preUpdate(CrmCustomerDO po) {
	    CrmCustomerDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果客户编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 客户编码已存在不能修改
	            assertByColumn(po.getCode(),CrmCustomerDO::getCode,false);
	        }
	    }

    
	}
}
