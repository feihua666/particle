package com.particle.crm.infrastructure.tag.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import com.particle.crm.infrastructure.tag.mapper.CrmCustomerTagMapper;
import com.particle.crm.infrastructure.tag.service.ICrmCustomerTagService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 客户标签 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Component
public class CrmCustomerTagServiceImpl extends IBaseServiceImpl<CrmCustomerTagMapper, CrmCustomerTagDO> implements ICrmCustomerTagService {
	private IBaseQueryCommandMapStruct<CrmCustomerTagDO> queryCommandMapStruct;

	@Override
	protected CrmCustomerTagDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCustomerTagDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCustomerTagDO po) {
	    // 标签编码 已存在不能添加
	    if (StrUtil.isNotEmpty(po.getCode())) {
	    	    assertByColumn(po.getCode(),CrmCustomerTagDO::getCode,false);
	    }
	    // 标签名称 已存在不能添加
	    if (StrUtil.isNotEmpty(po.getName())) {
	    	    assertByColumn(po.getName(),CrmCustomerTagDO::getName,false);
	    }
	}

	@Override
	protected void preUpdate(CrmCustomerTagDO po) {
	    CrmCustomerTagDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果标签编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 标签编码已存在不能修改
	            assertByColumn(po.getCode(),CrmCustomerTagDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果标签名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 标签名称已存在不能修改
	            assertByColumn(po.getName(),CrmCustomerTagDO::getName,false);
	        }
	    }


	}
}
