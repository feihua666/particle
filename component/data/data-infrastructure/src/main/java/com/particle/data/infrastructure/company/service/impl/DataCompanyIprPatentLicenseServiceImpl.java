package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentLicenseMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利许可信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Component
public class DataCompanyIprPatentLicenseServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentLicenseMapper, DataCompanyIprPatentLicenseDO> implements IDataCompanyIprPatentLicenseService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentLicenseDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentLicenseDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentLicenseDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentLicenseDO po) {
    
	}
}
