package com.particle.tracking.infrastructure.gateway.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.particle.global.dto.basic.IdCommand;
import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.TrackingPageRecordId;
import com.particle.tracking.domain.gateway.TrackingPageRecordGateway;
import com.particle.tracking.infrastructure.service.ITrackingPageRecordService;
import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import com.particle.tracking.infrastructure.structmapping.TrackingPageRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * <p>
 * 页面埋点记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Component
public class TrackingPageRecordGatewayImpl extends AbstractBaseGatewayImpl<TrackingPageRecordId,TrackingPageRecord> implements TrackingPageRecordGateway {

	private ITrackingPageRecordService iTrackingPageRecordService;

	@Override
	public TrackingPageRecord getById(TrackingPageRecordId trackingPageRecordId) {
		TrackingPageRecordDO byId = iTrackingPageRecordService.getById(trackingPageRecordId.getId());
		TrackingPageRecord trackingPageRecord = DomainFactory.create(TrackingPageRecord.class);
		trackingPageRecord = TrackingPageRecordInfrastructureStructMapping.instance. trackingPageRecordDOToTrackingPageRecord(trackingPageRecord,byId);
		return trackingPageRecord;
	}

	@Override
	public boolean doSave(TrackingPageRecord trackingPageRecord) {
		TrackingPageRecordDO trackingPageRecordDO = TrackingPageRecordInfrastructureStructMapping.instance.trackingPageRecordToTrackingPageRecordDO(trackingPageRecord);
		if (trackingPageRecordDO.getId() == null) {
			trackingPageRecordDO.setAddControl(trackingPageRecord.getAddControl());
			TrackingPageRecordDO add = iTrackingPageRecordService.add(trackingPageRecordDO);
			trackingPageRecord.setId(TrackingPageRecordId.of(add.getId()));
			return add != null;
		}
		trackingPageRecordDO.setUpdateControl(trackingPageRecord.getUpdateControl());
		TrackingPageRecordDO update = iTrackingPageRecordService.update(trackingPageRecordDO);
		// 如果更新有离开时间，计算一下持续时长
		if (trackingPageRecordDO.getLeaveAt() != null) {
			TrackingPageRecordDO byId = iTrackingPageRecordService.getById(trackingPageRecord.getId());
			if (byId.getEntryAt() != null && byId.getLeaveAt() != null && byId.getDuration() == null) {
				Duration between = LocalDateTimeUtil.between(byId.getEntryAt(), byId.getLeaveAt());
				byId.setDuration(between.toMillis());
				iTrackingPageRecordService.update(byId);
			}
		}
		return update != null;
	}

	@Override
	public boolean delete(TrackingPageRecordId trackingPageRecordId) {
		return iTrackingPageRecordService.deleteById(trackingPageRecordId.getId());
	}

	@Override
	public boolean delete(TrackingPageRecordId id, IdCommand idCommand) {
		return iTrackingPageRecordService.deleteById(idCommand);
	}

	@Autowired
	public void setITrackingPageRecordService(ITrackingPageRecordService iTrackingPageRecordService) {
		this.iTrackingPageRecordService = iTrackingPageRecordService;
	}

}
