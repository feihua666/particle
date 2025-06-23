package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyPrimeStaffPositionMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业主要人员职位 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Component
public class DataCompanyPrimeStaffPositionServiceImpl extends IBaseServiceImpl<DataCompanyPrimeStaffPositionMapper, DataCompanyPrimeStaffPositionDO> implements IDataCompanyPrimeStaffPositionService {
	private IBaseQueryCommandMapStruct<DataCompanyPrimeStaffPositionDO> queryCommandMapStruct;

	@Override
	protected DataCompanyPrimeStaffPositionDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyPrimeStaffPositionDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyPrimeStaffPositionDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyPrimeStaffPositionDO po) {
    
	}
}
