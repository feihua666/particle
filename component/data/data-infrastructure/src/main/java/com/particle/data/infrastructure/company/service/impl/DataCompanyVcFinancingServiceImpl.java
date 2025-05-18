package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyVcFinancingMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业融资 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Component
public class DataCompanyVcFinancingServiceImpl extends IBaseServiceImpl<DataCompanyVcFinancingMapper, DataCompanyVcFinancingDO> implements IDataCompanyVcFinancingService {
	private IBaseQueryCommandMapStruct<DataCompanyVcFinancingDO> queryCommandMapStruct;

	@Override
	protected DataCompanyVcFinancingDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyVcFinancingDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyVcFinancingDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyVcFinancingDO po) {
    
	}
}
