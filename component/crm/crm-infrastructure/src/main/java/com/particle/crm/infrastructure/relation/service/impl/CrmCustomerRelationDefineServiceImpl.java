package com.particle.crm.infrastructure.relation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDefineDO;
import com.particle.crm.infrastructure.relation.mapper.CrmCustomerRelationDefineMapper;
import com.particle.crm.infrastructure.relation.service.ICrmCustomerRelationDefineService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 客户关系定义 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Component
public class CrmCustomerRelationDefineServiceImpl extends IBaseServiceImpl<CrmCustomerRelationDefineMapper, CrmCustomerRelationDefineDO> implements ICrmCustomerRelationDefineService {
	private IBaseQueryCommandMapStruct<CrmCustomerRelationDefineDO> queryCommandMapStruct;

	@Override
	protected CrmCustomerRelationDefineDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCustomerRelationDefineDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCustomerRelationDefineDO po) {
	    // 关系定义编码 已存在不能添加
	    if (StrUtil.isNotEmpty(po.getCode())) {
	    	    assertByColumn(po.getCode(),CrmCustomerRelationDefineDO::getCode,false);
	    }
	    // 关系定义名称 已存在不能添加
	    if (StrUtil.isNotEmpty(po.getName())) {
	    	    assertByColumn(po.getName(),CrmCustomerRelationDefineDO::getName,false);
	    }
	}

	@Override
	protected void preUpdate(CrmCustomerRelationDefineDO po) {
	    CrmCustomerRelationDefineDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果关系定义编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 关系定义编码已存在不能修改
	            assertByColumn(po.getCode(),CrmCustomerRelationDefineDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果关系定义名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 关系定义名称已存在不能修改
	            assertByColumn(po.getName(),CrmCustomerRelationDefineDO::getName,false);
	        }
	    }

    
	}
}
