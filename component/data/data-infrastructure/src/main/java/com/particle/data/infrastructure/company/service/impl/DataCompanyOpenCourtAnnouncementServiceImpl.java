package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyOpenCourtAnnouncementMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业开庭公告 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Component
public class DataCompanyOpenCourtAnnouncementServiceImpl extends IBaseServiceImpl<DataCompanyOpenCourtAnnouncementMapper, DataCompanyOpenCourtAnnouncementDO> implements IDataCompanyOpenCourtAnnouncementService {
	private IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementDO> queryCommandMapStruct;

	private DataCompanyOpenCourtAnnouncementMapper dataCompanyOpenCourtAnnouncementMapper;

	@Override
	protected DataCompanyOpenCourtAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(DataCompanyOpenCourtAnnouncementDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyOpenCourtAnnouncementDO po) {

	}

	@Override
	public Page<DataCompanyOpenCourtAnnouncementDO> listPage(DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyOpenCourtAnnouncementMapper.listPage(pageQuery, param);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementMapper(DataCompanyOpenCourtAnnouncementMapper dataCompanyOpenCourtAnnouncementMapper) {
		this.dataCompanyOpenCourtAnnouncementMapper = dataCompanyOpenCourtAnnouncementMapper;
	}
}
