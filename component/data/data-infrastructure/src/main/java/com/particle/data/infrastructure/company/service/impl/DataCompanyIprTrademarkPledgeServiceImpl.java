package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkPledgeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权商标质押信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Component
public class DataCompanyIprTrademarkPledgeServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkPledgeMapper, DataCompanyIprTrademarkPledgeDO> implements IDataCompanyIprTrademarkPledgeService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkPledgeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprTrademarkPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkPledgeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkPledgeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkPledgeDO po) {
    
	}
}
