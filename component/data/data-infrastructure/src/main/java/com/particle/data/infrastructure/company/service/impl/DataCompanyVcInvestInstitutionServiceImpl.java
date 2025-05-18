package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyVcInvestInstitutionMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业投资机构 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Component
public class DataCompanyVcInvestInstitutionServiceImpl extends IBaseServiceImpl<DataCompanyVcInvestInstitutionMapper, DataCompanyVcInvestInstitutionDO> implements IDataCompanyVcInvestInstitutionService {
	private IBaseQueryCommandMapStruct<DataCompanyVcInvestInstitutionDO> queryCommandMapStruct;

	@Override
	protected DataCompanyVcInvestInstitutionDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyVcInvestInstitutionDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyVcInvestInstitutionDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyVcInvestInstitutionDO po) {
    
	}
}
