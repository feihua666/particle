package com.particle.dataconstraint.infrastructure.service.impl;

import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.dataconstraint.infrastructure.mapper.DataScopeCustomDataRelMapper;
import com.particle.dataconstraint.infrastructure.service.IDataScopeCustomDataRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 数据范围自定义数据关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Component
public class DataScopeCustomDataRelServiceImpl extends IBaseServiceImpl<DataScopeCustomDataRelMapper, DataScopeCustomDataRelDO> implements IDataScopeCustomDataRelService {
	private IBaseQueryCommandMapStruct<DataScopeCustomDataRelDO> queryCommandMapStruct;

	@Override
	protected DataScopeCustomDataRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataScopeCustomDataRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataScopeCustomDataRelDO po) {
	    // 数据范围id 已存在不能添加
	    assertByColumn(po.getDataScopeId(),DataScopeCustomDataRelDO::getDataScopeId,false);

	    // 自定义数据id 已存在不能添加
	    assertByColumn(po.getDataId(),DataScopeCustomDataRelDO::getDataId,false);

	}

	@Override
	protected void preUpdate(DataScopeCustomDataRelDO po) {
	    DataScopeCustomDataRelDO byId = null;
	    if (po.getDataScopeId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果数据范围id有改动
	        if (!po.getDataScopeId().equals(byId.getDataScopeId())) {
	            // 数据范围id已存在不能修改
	            assertByColumn(po.getDataScopeId(),DataScopeCustomDataRelDO::getDataScopeId,false);
	        }
	    }

	    if (po.getDataId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果自定义数据id有改动
	        if (!po.getDataId().equals(byId.getDataId())) {
	            // 自定义数据id已存在不能修改
	            assertByColumn(po.getDataId(),DataScopeCustomDataRelDO::getDataId,false);
	        }
	    }


	}
}
