package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyPersonMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业个人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Component
public class DataCompanyPersonServiceImpl extends IBaseServiceImpl<DataCompanyPersonMapper, DataCompanyPersonDO> implements IDataCompanyPersonService {
	private IBaseQueryCommandMapStruct<DataCompanyPersonDO> queryCommandMapStruct;

	@Override
	protected DataCompanyPersonDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyPersonDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyPersonDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyPersonDO po) {
    
	}
}
