package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentQuoteMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利引证信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Component
public class DataCompanyIprPatentQuoteServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentQuoteMapper, DataCompanyIprPatentQuoteDO> implements IDataCompanyIprPatentQuoteService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentQuoteDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentQuoteDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentQuoteDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentQuoteDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentQuoteDO po) {
    
	}
}
