package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportForeignGuaranteeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报对外担保 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Component
public class DataCompanyAnnualReportForeignGuaranteeServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportForeignGuaranteeMapper, DataCompanyAnnualReportForeignGuaranteeDO> implements IDataCompanyAnnualReportForeignGuaranteeService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportForeignGuaranteeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportForeignGuaranteeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportForeignGuaranteeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportForeignGuaranteeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportForeignGuaranteeDO po) {
    
	}
}
