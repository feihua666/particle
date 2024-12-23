package com.particle.data.infrastructure.temp.service.impl;

import com.particle.data.infrastructure.temp.dos.DataCompanyMd5IdsDO;
import com.particle.data.infrastructure.temp.mapper.DataCompanyMd5IdsMapper;
import com.particle.data.infrastructure.temp.service.IDataCompanyMd5IdsService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业md5ids 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Component
public class DataCompanyMd5IdsServiceImpl extends IBaseServiceImpl<DataCompanyMd5IdsMapper, DataCompanyMd5IdsDO> implements IDataCompanyMd5IdsService {
	private IBaseQueryCommandMapStruct<DataCompanyMd5IdsDO> queryCommandMapStruct;

	@Override
	protected DataCompanyMd5IdsDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyMd5IdsDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyMd5IdsDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyMd5IdsDO po) {

	}
}
