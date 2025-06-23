package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权商标当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Component
public class DataCompanyIprTrademarkPartyServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkPartyMapper, DataCompanyIprTrademarkPartyDO> implements IDataCompanyIprTrademarkPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprTrademarkPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkPartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkPartyDO po) {
    
	}
}
