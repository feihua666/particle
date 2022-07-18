package com.particle.area.infrastructure.gateway.impl;

import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import com.particle.area.domain.gateway.AreaGateway;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.structmapping.AreaInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 区域 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Component
public class AreaGatewayImpl extends AbstractBaseGatewayImpl implements AreaGateway {

	private IAreaService iAreaService;

	@Override
	public Area getById(AreaId areaId) {
		AreaDO byId = iAreaService.getById(areaId.getId());
		Area area = DomainFactory.create(Area.class);
		area = AreaInfrastructureStructMapping.instance. areaDOToArea(area,byId);
		return area;
	}

	@Override
	public boolean save(Area area) {
		AreaDO areaDO = AreaInfrastructureStructMapping.instance.areaToAreaDO(area);
		if (areaDO.getId() != null) {
			AreaDO add = iAreaService.add(areaDO);
			area.setId(AreaId.of(add.getId()));
			return add != null;
		}
		AreaDO update = iAreaService.update(areaDO);
		return update != null;
	}

	@Override
	public boolean delete(AreaId areaId) {
		return iAreaService.deleteById(areaId.getId());
	}

	@Autowired
	public void setAreaService(IAreaService iAreaService) {
		this.iAreaService = iAreaService;
	}
}
