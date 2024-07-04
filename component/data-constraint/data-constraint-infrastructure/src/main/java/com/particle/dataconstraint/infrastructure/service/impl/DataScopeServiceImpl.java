package com.particle.dataconstraint.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.infrastructure.mapper.DataScopeMapper;
import com.particle.dataconstraint.infrastructure.service.IDataScopeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 数据范围 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Component
public class DataScopeServiceImpl extends IBaseServiceImpl<DataScopeMapper, DataScopeDO> implements IDataScopeService {
	private IBaseQueryCommandMapStruct<DataScopeDO> queryCommandMapStruct;

	@Override
	protected DataScopeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataScopeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataScopeDO po) {
	    // 数据范围编码 已存在不能添加
	    assertByColumn(po.getCode(),DataScopeDO::getCode,false);

	}

	@Override
	protected void preUpdate(DataScopeDO po) {
	    DataScopeDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果数据范围编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 数据范围编码已存在不能修改
	            assertByColumn(po.getCode(),DataScopeDO::getCode,false);
	        }
	    }

    
	}
}
