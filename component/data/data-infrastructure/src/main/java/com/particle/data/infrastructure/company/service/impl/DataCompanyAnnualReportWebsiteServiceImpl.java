package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportWebsiteMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报网站网店 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Component
public class DataCompanyAnnualReportWebsiteServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportWebsiteMapper, DataCompanyAnnualReportWebsiteDO> implements IDataCompanyAnnualReportWebsiteService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportWebsiteDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportWebsiteDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportWebsiteDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportWebsiteDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportWebsiteDO po) {
    
	}
}
