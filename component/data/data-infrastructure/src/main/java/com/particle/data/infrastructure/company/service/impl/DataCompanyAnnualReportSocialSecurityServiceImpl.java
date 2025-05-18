package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportSocialSecurityMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报社保 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Component
public class DataCompanyAnnualReportSocialSecurityServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportSocialSecurityMapper, DataCompanyAnnualReportSocialSecurityDO> implements IDataCompanyAnnualReportSocialSecurityService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportSocialSecurityDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportSocialSecurityDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportSocialSecurityDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportSocialSecurityDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportSocialSecurityDO po) {
    
	}
}
