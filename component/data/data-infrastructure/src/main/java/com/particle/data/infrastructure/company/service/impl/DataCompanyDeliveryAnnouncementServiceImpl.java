package com.particle.data.infrastructure.company.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyDeliveryAnnouncementListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyDeliveryAnnouncementMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业送达公告 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Component
public class DataCompanyDeliveryAnnouncementServiceImpl extends IBaseServiceImpl<DataCompanyDeliveryAnnouncementMapper, DataCompanyDeliveryAnnouncementDO> implements IDataCompanyDeliveryAnnouncementService {
	private IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementDO> queryCommandMapStruct;

	private DataCompanyDeliveryAnnouncementMapper dataCompanyDeliveryAnnouncementMapper;

	@Override
	protected DataCompanyDeliveryAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Override
	public Page<DataCompanyDeliveryAnnouncementDO> listPage(DataCompanyDeliveryAnnouncementListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyDeliveryAnnouncementMapper.listPage(pageQuery, param);
	}
	@Override
	protected void preAdd(DataCompanyDeliveryAnnouncementDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyDeliveryAnnouncementDO po) {
	}
	@Autowired
	public void setDataCompanyDeliveryAnnouncementMapper(DataCompanyDeliveryAnnouncementMapper dataCompanyDeliveryAnnouncementMapper) {
		this.dataCompanyDeliveryAnnouncementMapper = dataCompanyDeliveryAnnouncementMapper;
	}
}
