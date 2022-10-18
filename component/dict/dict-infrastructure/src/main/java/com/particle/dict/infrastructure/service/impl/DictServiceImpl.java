package com.particle.dict.infrastructure.service.impl;

import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.mapper.DictMapper;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
public class DictServiceImpl extends IBaseServiceImpl<DictMapper, DictDO> implements IDictService {


	private IBaseQueryCommandMapStruct<DictDO> queryCommandMapStruct;

	@Override
	protected DictDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DictDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
