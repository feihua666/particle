package com.particle.data.infrastructure.company.service.impl;

import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyOpenCourtAnnouncementPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业开庭公告当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Component
public class DataCompanyOpenCourtAnnouncementPartyServiceImpl extends IBaseServiceImpl<DataCompanyOpenCourtAnnouncementPartyMapper, DataCompanyOpenCourtAnnouncementPartyDO> implements IDataCompanyOpenCourtAnnouncementPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyOpenCourtAnnouncementPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyOpenCourtAnnouncementPartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyOpenCourtAnnouncementPartyDO po) {
	}
}
