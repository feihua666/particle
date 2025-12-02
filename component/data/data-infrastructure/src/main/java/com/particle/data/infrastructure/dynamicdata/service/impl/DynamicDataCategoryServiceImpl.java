package com.particle.data.infrastructure.dynamicdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataCategoryMapper;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataCategoryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 动态数据分类 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Component
public class DynamicDataCategoryServiceImpl extends IBaseServiceImpl<DynamicDataCategoryMapper, DynamicDataCategoryDO> implements IDynamicDataCategoryService {
	private IBaseQueryCommandMapStruct<DynamicDataCategoryDO> queryCommandMapStruct;

	@Override
	protected DynamicDataCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicDataCategoryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicDataCategoryDO po) {
	}

	@Override
	protected void preUpdate(DynamicDataCategoryDO po) {
    
	}
}
