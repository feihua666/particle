package com.particle.dream.infrastructure.ssq.gateway.impl;

import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.SsqCodeOpenedId;
import com.particle.dream.domain.ssq.gateway.SsqCodeOpenedGateway;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeOpenedService;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.structmapping.SsqCodeOpenedInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 双色球开奖 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Component
public class SsqCodeOpenedGatewayImpl extends AbstractBaseGatewayImpl<SsqCodeOpenedId,SsqCodeOpened> implements SsqCodeOpenedGateway {

	private ISsqCodeOpenedService iSsqCodeOpenedService;

	@Override
	public SsqCodeOpened getById(SsqCodeOpenedId ssqCodeOpenedId) {
		SsqCodeOpenedDO byId = iSsqCodeOpenedService.getById(ssqCodeOpenedId.getId());
		SsqCodeOpened ssqCodeOpened = DomainFactory.create(SsqCodeOpened.class);
		ssqCodeOpened = SsqCodeOpenedInfrastructureStructMapping.instance. ssqCodeOpenedDOToSsqCodeOpened(ssqCodeOpened,byId);
		return ssqCodeOpened;
	}

	@Override
	public boolean doSave(SsqCodeOpened ssqCodeOpened) {
		SsqCodeOpenedDO ssqCodeOpenedDO = SsqCodeOpenedInfrastructureStructMapping.instance.ssqCodeOpenedToSsqCodeOpenedDO(ssqCodeOpened);
		if (ssqCodeOpenedDO.getId() == null) {
			ssqCodeOpenedDO.setAddControl(ssqCodeOpened.getAddControl());
			SsqCodeOpenedDO add = iSsqCodeOpenedService.add(ssqCodeOpenedDO);
			ssqCodeOpened.setId(SsqCodeOpenedId.of(add.getId()));
			return add != null;
		}
		ssqCodeOpenedDO.setUpdateControl(ssqCodeOpened.getUpdateControl());
		SsqCodeOpenedDO update = iSsqCodeOpenedService.update(ssqCodeOpenedDO);
		return update != null;
	}

	@Override
	public boolean delete(SsqCodeOpenedId ssqCodeOpenedId) {
		return iSsqCodeOpenedService.deleteById(ssqCodeOpenedId.getId());
	}


	@Autowired
	public void setISsqCodeOpenedService(ISsqCodeOpenedService iSsqCodeOpenedService) {
		this.iSsqCodeOpenedService = iSsqCodeOpenedService;
	}
}
