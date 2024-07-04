package com.particle.role.infrastructure.roledatascoperel.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.infrastructure.roledatascoperel.mapper.RoleDataScopeRelMapper;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 角色数据范围关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Component
public class RoleDataScopeRelServiceImpl extends IBaseServiceImpl<RoleDataScopeRelMapper, RoleDataScopeRelDO> implements IRoleDataScopeRelService {
	private IBaseQueryCommandMapStruct<RoleDataScopeRelDO> queryCommandMapStruct;

	@Override
	protected RoleDataScopeRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<RoleDataScopeRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(RoleDataScopeRelDO po) {
	    // 角色id 已存在不能添加
	    assertByColumn(po.getRoleId(),RoleDataScopeRelDO::getRoleId,false);

	    // 数据范围id 已存在不能添加
	    assertByColumn(po.getDataScopeId(),RoleDataScopeRelDO::getDataScopeId,false);

	}

	@Override
	protected void preUpdate(RoleDataScopeRelDO po) {
	    RoleDataScopeRelDO byId = null;
	    if (po.getRoleId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果角色id有改动
	        if (!po.getRoleId().equals(byId.getRoleId())) {
	            // 角色id已存在不能修改
	            assertByColumn(po.getRoleId(),RoleDataScopeRelDO::getRoleId,false);
	        }
	    }

	    if (po.getDataScopeId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果数据范围id有改动
	        if (!po.getDataScopeId().equals(byId.getDataScopeId())) {
	            // 数据范围id已存在不能修改
	            assertByColumn(po.getDataScopeId(),RoleDataScopeRelDO::getDataScopeId,false);
	        }
	    }

    
	}
}
