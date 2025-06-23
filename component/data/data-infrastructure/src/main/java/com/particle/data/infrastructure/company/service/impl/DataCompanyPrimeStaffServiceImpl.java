package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyPrimeStaffMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业主要人员 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Component
public class DataCompanyPrimeStaffServiceImpl extends IBaseServiceImpl<DataCompanyPrimeStaffMapper, DataCompanyPrimeStaffDO> implements IDataCompanyPrimeStaffService {
	private IBaseQueryCommandMapStruct<DataCompanyPrimeStaffDO> queryCommandMapStruct;

	@Override
	protected DataCompanyPrimeStaffDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyPrimeStaffDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyPrimeStaffDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyPrimeStaffDO po) {
    
	}
}
