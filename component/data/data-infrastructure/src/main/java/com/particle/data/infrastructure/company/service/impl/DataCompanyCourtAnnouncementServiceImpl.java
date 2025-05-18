package com.particle.data.infrastructure.company.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyCourtAnnouncementListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyCourtAnnouncementMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业法院公告 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Component
public class DataCompanyCourtAnnouncementServiceImpl extends IBaseServiceImpl<DataCompanyCourtAnnouncementMapper, DataCompanyCourtAnnouncementDO> implements IDataCompanyCourtAnnouncementService {
	private IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementDO> queryCommandMapStruct;

	private DataCompanyCourtAnnouncementMapper dataCompanyCourtAnnouncementMapper;

	@Override
	protected DataCompanyCourtAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}

	@Override
	protected void preAdd(DataCompanyCourtAnnouncementDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyCourtAnnouncementDO po) {

	}

	@Override
	public Page<DataCompanyCourtAnnouncementDO> listPage(DataCompanyCourtAnnouncementListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyCourtAnnouncementMapper.listPage(pageQuery, param);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementMapper(DataCompanyCourtAnnouncementMapper dataCompanyCourtAnnouncementMapper) {
		this.dataCompanyCourtAnnouncementMapper = dataCompanyCourtAnnouncementMapper;
	}
}
