package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyCourtAnnouncementPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


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
	    // 法院公告表id 已存在不能添加
	    assertByColumn(po.getCompanyCourtAnnouncementId(),DataCompanyCourtAnnouncementPartyDO::getCompanyCourtAnnouncementId,false);

	}

	@Override
	protected void preUpdate(DataCompanyCourtAnnouncementPartyDO po) {
	    DataCompanyCourtAnnouncementPartyDO byId = null;
	    if (po.getCompanyCourtAnnouncementId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果法院公告表id有改动
	        if (!po.getCompanyCourtAnnouncementId().equals(byId.getCompanyCourtAnnouncementId())) {
	            // 法院公告表id已存在不能修改
	            assertByColumn(po.getCompanyCourtAnnouncementId(),DataCompanyCourtAnnouncementPartyDO::getCompanyCourtAnnouncementId,false);
	        }
	    }

    
	}
}
