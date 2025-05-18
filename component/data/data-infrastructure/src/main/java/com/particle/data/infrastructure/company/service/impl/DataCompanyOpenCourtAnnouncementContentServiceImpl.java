package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyOpenCourtAnnouncementContentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业开庭公告内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Component
public class DataCompanyOpenCourtAnnouncementContentServiceImpl extends IBaseServiceImpl<DataCompanyOpenCourtAnnouncementContentMapper, DataCompanyOpenCourtAnnouncementContentDO> implements IDataCompanyOpenCourtAnnouncementContentService {
	private IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementContentDO> queryCommandMapStruct;

	@Override
	protected DataCompanyOpenCourtAnnouncementContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyOpenCourtAnnouncementContentDO po) {
	    // 企业开庭公告id 已存在不能添加
	    assertByColumn(po.getCompanyOpenCourtAnnouncementId(),DataCompanyOpenCourtAnnouncementContentDO::getCompanyOpenCourtAnnouncementId,false);

	}

	@Override
	protected void preUpdate(DataCompanyOpenCourtAnnouncementContentDO po) {
	    DataCompanyOpenCourtAnnouncementContentDO byId = null;
	    if (po.getCompanyOpenCourtAnnouncementId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果企业开庭公告id有改动
	        if (!po.getCompanyOpenCourtAnnouncementId().equals(byId.getCompanyOpenCourtAnnouncementId())) {
	            // 企业开庭公告id已存在不能修改
	            assertByColumn(po.getCompanyOpenCourtAnnouncementId(),DataCompanyOpenCourtAnnouncementContentDO::getCompanyOpenCourtAnnouncementId,false);
	        }
	    }

    
	}
}
