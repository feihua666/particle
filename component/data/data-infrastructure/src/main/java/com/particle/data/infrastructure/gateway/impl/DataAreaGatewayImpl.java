package com.particle.data.infrastructure.gateway.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.area.adapter.feign.client.rpc.AreaRpcFeignClient;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.data.common.tool.DataAreaItemInfo;
import com.particle.data.domain.gateway.DataAreaGateway;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 区域依赖
 * </p>
 *
 * @author yangwei
 * @since 2025-05-09 16:10:18
 */
@Component
public class DataAreaGatewayImpl implements DataAreaGateway {


	private AreaRpcFeignClient areaRpcFeignClient;


	@Override
	public DataAreaItemInfo getProvinceByName(String provinceName) {
        if (StrUtil.isEmpty(provinceName)) {
            return null;
        }
		SingleResponse<AreaVO> areaVOSingleResponse = areaRpcFeignClient.queryProvinceByName(provinceName);
		AreaVO areaVO = areaVOSingleResponse.getData();
        if (areaVO != null) {
			return DataAreaItemInfo.create(areaVO.getId(), areaVO.getCode(), areaVO.getName());
        }
		return null;
	}

	@Override
	public DataAreaItemInfo getCityByNameAndProvinceId(String cityName, Long provinceId) {
		if (StrUtil.isEmpty(cityName) || provinceId == null) {
			return null;
		}
		SingleResponse<AreaVO> areaVOSingleResponse = areaRpcFeignClient.queryCityByNameAndProvinceId(cityName,provinceId);
		AreaVO areaVO = areaVOSingleResponse.getData();
		if (areaVO != null) {
			return DataAreaItemInfo.create(areaVO.getId(), areaVO.getCode(), areaVO.getName());
		}
		return null;
	}

	@Override
	public DataAreaItemInfo getCountyByNameAndCityId(String countyName, Long cityId) {
		if (StrUtil.isEmpty(countyName) || cityId == null) {
			return null;
		}
		SingleResponse<AreaVO> areaVOSingleResponse = areaRpcFeignClient.queryCountyByNameAndCityId(countyName, cityId);
		AreaVO areaVO = areaVOSingleResponse.getData();
		if (areaVO != null) {
			return DataAreaItemInfo.create(areaVO.getId(), areaVO.getCode(), areaVO.getName());
		}
		return null;
	}


	@Autowired
	public void setAreaRpcFeignClient(AreaRpcFeignClient areaRpcFeignClient) {
		this.areaRpcFeignClient = areaRpcFeignClient;
	}

}
