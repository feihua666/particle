package com.particle.area.infrastructure.service.impl;

import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.mapper.AreaMapper;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 区域 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Component
public class AreaServiceImpl extends IBaseServiceImpl<AreaMapper, AreaDO> implements IAreaService {


	private IBaseQueryCommandMapStruct<AreaDO> queryCommandMapStruct;

	@Override
	protected AreaDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AreaDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
