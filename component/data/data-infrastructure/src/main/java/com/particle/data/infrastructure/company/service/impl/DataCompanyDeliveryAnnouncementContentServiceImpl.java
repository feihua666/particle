package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyDeliveryAnnouncementContentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业送达公告内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Component
public class DataCompanyDeliveryAnnouncementContentServiceImpl extends IBaseServiceImpl<DataCompanyDeliveryAnnouncementContentMapper, DataCompanyDeliveryAnnouncementContentDO> implements IDataCompanyDeliveryAnnouncementContentService {
	private IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementContentDO> queryCommandMapStruct;

	@Override
	protected DataCompanyDeliveryAnnouncementContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyDeliveryAnnouncementContentDO po) {
	    // 企业送达公告id 已存在不能添加
	    assertByColumn(po.getCompanyDeliveryAnnouncementId(),DataCompanyDeliveryAnnouncementContentDO::getCompanyDeliveryAnnouncementId,false);

	}

	@Override
	protected void preUpdate(DataCompanyDeliveryAnnouncementContentDO po) {
	    DataCompanyDeliveryAnnouncementContentDO byId = null;
	    if (po.getCompanyDeliveryAnnouncementId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果企业送达公告id有改动
	        if (!po.getCompanyDeliveryAnnouncementId().equals(byId.getCompanyDeliveryAnnouncementId())) {
	            // 企业送达公告id已存在不能修改
	            assertByColumn(po.getCompanyDeliveryAnnouncementId(),DataCompanyDeliveryAnnouncementContentDO::getCompanyDeliveryAnnouncementId,false);
	        }
	    }

    
	}
}
