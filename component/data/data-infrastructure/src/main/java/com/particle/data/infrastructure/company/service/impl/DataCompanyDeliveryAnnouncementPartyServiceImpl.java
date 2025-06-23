package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyDeliveryAnnouncementPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业送达公告当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Component
public class DataCompanyDeliveryAnnouncementPartyServiceImpl extends IBaseServiceImpl<DataCompanyDeliveryAnnouncementPartyMapper, DataCompanyDeliveryAnnouncementPartyDO> implements IDataCompanyDeliveryAnnouncementPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyDeliveryAnnouncementPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyDeliveryAnnouncementPartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyDeliveryAnnouncementPartyDO po) {
    
	}
}
