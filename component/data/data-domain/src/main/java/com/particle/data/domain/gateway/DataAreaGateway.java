package com.particle.data.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.data.common.tool.DataAreaItemInfo;

/**
 * <p>
 * 数据模块依赖区域
 * </p>
 *
 * @author yangwei
 * @since 2025-05-09 16:06:33
 */
public interface DataAreaGateway extends IGateway {
	/**
     * 获取省信息
	 * @param provinceName
     * @return
	 */
	DataAreaItemInfo getProvinceByName(String provinceName);

	/**
	 * 获取市信息
	 * @param cityName
	 * @param provinceId
	 * @return
	 */
	DataAreaItemInfo getCityByNameAndProvinceId(String cityName,Long provinceId);

	/**
	 * 获取区县信息
	 * @param countyName
	 * @param cityId
	 * @return
	 */
	DataAreaItemInfo getCountyByNameAndCityId(String countyName,Long cityId);
}
