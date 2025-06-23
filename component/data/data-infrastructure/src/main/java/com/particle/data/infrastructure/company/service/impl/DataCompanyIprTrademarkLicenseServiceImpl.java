package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkLicenseMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权商标许可信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Component
public class DataCompanyIprTrademarkLicenseServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkLicenseMapper, DataCompanyIprTrademarkLicenseDO> implements IDataCompanyIprTrademarkLicenseService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkLicenseDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprTrademarkLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkLicenseDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkLicenseDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkLicenseDO po) {
    
	}
}
