package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportAssetsMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAssetsService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业资产状况信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Component
public class DataCompanyAnnualReportAssetsServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportAssetsMapper, DataCompanyAnnualReportAssetsDO> implements IDataCompanyAnnualReportAssetsService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportAssetsDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportAssetsDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportAssetsDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportAssetsDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportAssetsDO po) {
    
	}
}
