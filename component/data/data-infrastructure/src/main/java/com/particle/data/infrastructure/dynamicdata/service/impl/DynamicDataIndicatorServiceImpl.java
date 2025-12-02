package com.particle.data.infrastructure.dynamicdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataIndicatorMapper;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 动态数据指标 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Component
public class DynamicDataIndicatorServiceImpl extends IBaseServiceImpl<DynamicDataIndicatorMapper, DynamicDataIndicatorDO> implements IDynamicDataIndicatorService {
	private IBaseQueryCommandMapStruct<DynamicDataIndicatorDO> queryCommandMapStruct;

	@Override
	protected DynamicDataIndicatorDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicDataIndicatorDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicDataIndicatorDO po) {
	}

	@Override
	protected void preUpdate(DynamicDataIndicatorDO po) {
    
	}
}
