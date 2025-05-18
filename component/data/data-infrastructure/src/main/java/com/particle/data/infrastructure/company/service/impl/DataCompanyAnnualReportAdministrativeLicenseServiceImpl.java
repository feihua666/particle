package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportAdministrativeLicenseMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAdministrativeLicenseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报行政许可 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Component
public class DataCompanyAnnualReportAdministrativeLicenseServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportAdministrativeLicenseMapper, DataCompanyAnnualReportAdministrativeLicenseDO> implements IDataCompanyAnnualReportAdministrativeLicenseService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportAdministrativeLicenseDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportAdministrativeLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportAdministrativeLicenseDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportAdministrativeLicenseDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportAdministrativeLicenseDO po) {
    
	}
}
