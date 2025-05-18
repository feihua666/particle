package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportForeignInvestMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报对外投资 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Component
public class DataCompanyAnnualReportForeignInvestServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportForeignInvestMapper, DataCompanyAnnualReportForeignInvestDO> implements IDataCompanyAnnualReportForeignInvestService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportForeignInvestDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportForeignInvestDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportForeignInvestDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportForeignInvestDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportForeignInvestDO po) {
    
	}
}
