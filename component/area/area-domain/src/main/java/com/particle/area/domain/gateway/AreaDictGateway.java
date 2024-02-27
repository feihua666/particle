package com.particle.area.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 区域依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2024-02-27 11:02:44
 */
public interface AreaDictGateway extends IGateway {
	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
