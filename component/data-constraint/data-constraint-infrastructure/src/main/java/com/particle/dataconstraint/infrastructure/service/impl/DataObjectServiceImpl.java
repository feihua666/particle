package com.particle.dataconstraint.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.dataconstraint.infrastructure.mapper.DataObjectMapper;
import com.particle.dataconstraint.infrastructure.service.IDataObjectService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 数据对象 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Component
public class DataObjectServiceImpl extends IBaseServiceImpl<DataObjectMapper, DataObjectDO> implements IDataObjectService {
	private IBaseQueryCommandMapStruct<DataObjectDO> queryCommandMapStruct;

	@Override
	protected DataObjectDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataObjectDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataObjectDO po) {
	    // 数据对象编码 已存在不能添加
	    assertByColumn(po.getCode(),DataObjectDO::getCode,false);

	}

	@Override
	protected void preUpdate(DataObjectDO po) {
	    DataObjectDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果数据对象编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 数据对象编码已存在不能修改
	            assertByColumn(po.getCode(),DataObjectDO::getCode,false);
	        }
	    }

    
	}
}
