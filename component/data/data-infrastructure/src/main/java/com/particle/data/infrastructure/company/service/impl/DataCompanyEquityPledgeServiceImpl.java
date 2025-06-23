package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyEquityPledgeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业股权出质 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Component
public class DataCompanyEquityPledgeServiceImpl extends IBaseServiceImpl<DataCompanyEquityPledgeMapper, DataCompanyEquityPledgeDO> implements IDataCompanyEquityPledgeService {
	private IBaseQueryCommandMapStruct<DataCompanyEquityPledgeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyEquityPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyEquityPledgeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyEquityPledgeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyEquityPledgeDO po) {
    
	}
}
