package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkLicensePersonMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权商标许可人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Component
public class DataCompanyIprTrademarkLicensePersonServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkLicensePersonMapper, DataCompanyIprTrademarkLicensePersonDO> implements IDataCompanyIprTrademarkLicensePersonService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkLicensePersonDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprTrademarkLicensePersonDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkLicensePersonDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkLicensePersonDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkLicensePersonDO po) {
    
	}
}
