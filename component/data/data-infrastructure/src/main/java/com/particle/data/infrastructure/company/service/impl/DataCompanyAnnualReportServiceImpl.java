package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Component
public class DataCompanyAnnualReportServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportMapper, DataCompanyAnnualReportDO> implements IDataCompanyAnnualReportService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportDO po) {
    
	}
}
