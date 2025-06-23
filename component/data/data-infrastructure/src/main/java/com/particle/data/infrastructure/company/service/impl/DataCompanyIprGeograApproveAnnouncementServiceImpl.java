package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprGeograApproveAnnouncementMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权地理标识核准公告 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Component
public class DataCompanyIprGeograApproveAnnouncementServiceImpl extends IBaseServiceImpl<DataCompanyIprGeograApproveAnnouncementMapper, DataCompanyIprGeograApproveAnnouncementDO> implements IDataCompanyIprGeograApproveAnnouncementService {
	private IBaseQueryCommandMapStruct<DataCompanyIprGeograApproveAnnouncementDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprGeograApproveAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprGeograApproveAnnouncementDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprGeograApproveAnnouncementDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprGeograApproveAnnouncementDO po) {
    
	}
}
