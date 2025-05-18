package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyVcFinancingInvestInstitutionRelMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业融资历史投资机构关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Component
public class DataCompanyVcFinancingInvestInstitutionRelServiceImpl extends IBaseServiceImpl<DataCompanyVcFinancingInvestInstitutionRelMapper, DataCompanyVcFinancingInvestInstitutionRelDO> implements IDataCompanyVcFinancingInvestInstitutionRelService {
	private IBaseQueryCommandMapStruct<DataCompanyVcFinancingInvestInstitutionRelDO> queryCommandMapStruct;

	@Override
	protected DataCompanyVcFinancingInvestInstitutionRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyVcFinancingInvestInstitutionRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyVcFinancingInvestInstitutionRelDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyVcFinancingInvestInstitutionRelDO po) {
    
	}
}
