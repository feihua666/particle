package com.particle.data.infrastructure.company.service.impl;

import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyCourtAnnouncementPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业法院公告当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Component
public class DataCompanyCourtAnnouncementPartyServiceImpl extends IBaseServiceImpl<DataCompanyCourtAnnouncementPartyMapper, DataCompanyCourtAnnouncementPartyDO> implements IDataCompanyCourtAnnouncementPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyCourtAnnouncementPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyCourtAnnouncementPartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyCourtAnnouncementPartyDO po) {
	}
}
