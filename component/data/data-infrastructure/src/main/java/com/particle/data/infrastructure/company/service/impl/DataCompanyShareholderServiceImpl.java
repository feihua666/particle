package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyShareholderMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业股东 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Component
public class DataCompanyShareholderServiceImpl extends IBaseServiceImpl<DataCompanyShareholderMapper, DataCompanyShareholderDO> implements IDataCompanyShareholderService {
	private IBaseQueryCommandMapStruct<DataCompanyShareholderDO> queryCommandMapStruct;

	@Override
	protected DataCompanyShareholderDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyShareholderDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyShareholderDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyShareholderDO po) {
    
	}
}
