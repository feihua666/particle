package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportShareholderMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报股东 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Component
public class DataCompanyAnnualReportShareholderServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportShareholderMapper, DataCompanyAnnualReportShareholderDO> implements IDataCompanyAnnualReportShareholderService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportShareholderDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportShareholderDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportShareholderDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportShareholderDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportShareholderDO po) {
    
	}
}
