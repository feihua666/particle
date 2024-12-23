package com.particle.dataquery.infrastructure.provider.service.impl;

import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.dataquery.infrastructure.provider.mapper.DataQueryProviderMapper;
import com.particle.dataquery.infrastructure.provider.service.IDataQueryProviderService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 数据查询供应商 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Component
public class DataQueryProviderServiceImpl extends IBaseServiceImpl<DataQueryProviderMapper, DataQueryProviderDO> implements IDataQueryProviderService {
	private IBaseQueryCommandMapStruct<DataQueryProviderDO> queryCommandMapStruct;

	@Override
	protected DataQueryProviderDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataQueryProviderDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataQueryProviderDO po) {
	}

	@Override
	protected void preUpdate(DataQueryProviderDO po) {

	}
}
