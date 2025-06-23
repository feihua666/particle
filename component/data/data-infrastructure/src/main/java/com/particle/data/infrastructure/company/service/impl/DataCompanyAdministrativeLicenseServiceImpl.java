package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAdministrativeLicenseMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业行政许可 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Component
public class DataCompanyAdministrativeLicenseServiceImpl extends IBaseServiceImpl<DataCompanyAdministrativeLicenseMapper, DataCompanyAdministrativeLicenseDO> implements IDataCompanyAdministrativeLicenseService {
	private IBaseQueryCommandMapStruct<DataCompanyAdministrativeLicenseDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAdministrativeLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAdministrativeLicenseDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAdministrativeLicenseDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAdministrativeLicenseDO po) {
    
	}
}
