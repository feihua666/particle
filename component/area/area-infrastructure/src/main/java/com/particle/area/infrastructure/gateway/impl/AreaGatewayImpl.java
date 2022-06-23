package com.particle.area.infrastructure.gateway.impl;

import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import com.particle.area.domain.gateway.AreaGateway;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 区域网关实现
 * </p>
 *
 * @author yangwei
 * @since 2022-05-16 22:30
 */
@Component
public class AreaGatewayImpl extends AbstractBaseGatewayImpl implements AreaGateway {
	@Override
	public Area getById(AreaId areaId) {
		return null;
	}

	@Override
	public boolean save(Area area) {
		return false;
	}
}
