package com.particle.tracking.infrastructure.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.TrackingPageId;
import com.particle.tracking.domain.gateway.TrackingPageGateway;
import com.particle.tracking.infrastructure.service.ITrackingPageService;
import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import com.particle.tracking.infrastructure.structmapping.TrackingPageInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 埋点页面 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Component
public class TrackingPageGatewayImpl extends AbstractBaseGatewayImpl<TrackingPageId,TrackingPage> implements TrackingPageGateway {

	private ITrackingPageService iTrackingPageService;

	@Override
	public TrackingPage getById(TrackingPageId trackingPageId) {
		TrackingPageDO byId = iTrackingPageService.getById(trackingPageId.getId());
		TrackingPage trackingPage = DomainFactory.create(TrackingPage.class);
		trackingPage = TrackingPageInfrastructureStructMapping.instance. trackingPageDOToTrackingPage(trackingPage,byId);
		return trackingPage;
	}

	@Override
	public boolean doSave(TrackingPage trackingPage) {
		TrackingPageDO trackingPageDO = TrackingPageInfrastructureStructMapping.instance.trackingPageToTrackingPageDO(trackingPage);
		if (trackingPageDO.getId() == null) {
			trackingPageDO.setAddControl(trackingPage.getAddControl());
			TrackingPageDO add = iTrackingPageService.add(trackingPageDO);
			trackingPage.setId(TrackingPageId.of(add.getId()));
			return add != null;
		}
		trackingPageDO.setUpdateControl(trackingPage.getUpdateControl());
		TrackingPageDO update = iTrackingPageService.update(trackingPageDO);
		return update != null;
	}

	@Override
	public boolean delete(TrackingPageId trackingPageId) {
		return iTrackingPageService.deleteById(trackingPageId.getId());
	}

	@Override
	public boolean delete(TrackingPageId id, IdCommand idCommand) {
		return iTrackingPageService.deleteById(idCommand);
	}

	@Autowired
	public void setITrackingPageService(ITrackingPageService iTrackingPageService) {
		this.iTrackingPageService = iTrackingPageService;
	}
}
