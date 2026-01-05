package com.particle.componentadmin.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.componentadmin.infrastructure.mapper.AdminComponentMapper;
import com.particle.componentadmin.infrastructure.service.IAdminComponentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 组件 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Component
public class AdminComponentServiceImpl extends IBaseServiceImpl<AdminComponentMapper, AdminComponentDO> implements IAdminComponentService {
	private IBaseQueryCommandMapStruct<AdminComponentDO> queryCommandMapStruct;

	@Override
	protected AdminComponentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AdminComponentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AdminComponentDO po) {
	    // 组件英文名称 已存在不能添加
	    assertByColumn(po.getCode(),AdminComponentDO::getCode,false);

	}

	@Override
	protected void preUpdate(AdminComponentDO po) {
	    AdminComponentDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果组件英文名称有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 组件英文名称已存在不能修改
	            assertByColumn(po.getCode(),AdminComponentDO::getCode,false);
	        }
	    }

    
	}
}
