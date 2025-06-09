package com.particle.data.infrastructure.company.service.impl;

import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyRestrictHighConsumePartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业限制高消费当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
public class DataCompanyRestrictHighConsumePartyServiceImpl extends IBaseServiceImpl<DataCompanyRestrictHighConsumePartyMapper, DataCompanyRestrictHighConsumePartyDO> implements IDataCompanyRestrictHighConsumePartyService {
	private IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumePartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyRestrictHighConsumePartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumePartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyRestrictHighConsumePartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyRestrictHighConsumePartyDO po) {
	}
}
