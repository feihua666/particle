package com.particle.area.adapter.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.area.adapter.feign.client.rpc.AreaRpcFeignClient;
import com.particle.area.app.structmapping.AreaAppStructMapping;
import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.domain.enums.AreaType;
import com.particle.area.domain.gateway.AreaDictGateway;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 区域远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Tag(name = "区域远程调用相关接口")
@RestController
@RequestMapping("/rpc/area")
public class AreaRpcController extends AbstractBaseRpcAdapter implements AreaRpcFeignClient {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;
	@Autowired
	private IAreaService  iAreaService;
	@Autowired
	private AreaDictGateway areaDictGateway;


	@Cacheable(cacheNames = {"areaRpcControllerCache_queryById"})
	@Operation(summary = "根据区域id查询")
	@Override
	public SingleResponse<AreaVO> queryById(Long id) {
		AreaDO areaDO = iAreaService.getById(id);
		return SingleResponse.of(AreaAppStructMapping.instance.areaDOToAreaVO(areaDO));
	}
	@Cacheable(cacheNames = {"areaRpcControllerCache_queryByCode"})
	@Operation(summary = "根据区域code查询")
	@Override
	public SingleResponse<AreaVO> queryByCode(String code) {
		AreaDO areaDO = iAreaService.getByCode(code);
		return SingleResponse.of(AreaAppStructMapping.instance.areaDOToAreaVO(areaDO));
	}
	@Cacheable(cacheNames = {"areaRpcControllerCache_queryProvinceByName"})
	@Operation(summary = "根据名称查询省")
	@Override
	public SingleResponse<AreaVO> queryProvinceByName(String provinceName) {
		Long provinceDictId = areaDictGateway.getDictIdByGroupCodeAndItemValue(AreaType.Group.area_type.groupCode(), AreaType.province.itemValue());
		AreaDO areaDO = iAreaService.getByNameAndTypeDictId(provinceName,provinceDictId);
		return SingleResponse.of(AreaAppStructMapping.instance.areaDOToAreaVO(areaDO));
	}
	@Cacheable(cacheNames = {"areaRpcControllerCache_queryCityByNameAndProvinceId"})
	@Operation(summary = "根据名称查询市")
	@Override
	public SingleResponse<AreaVO> queryCityByNameAndProvinceId(String cityName, Long provinceId) {
        if (StrUtil.equalsAny(cityName, "北京市","天津市","太原市","上海市")) {
            cityName = "市辖区";
        }
		Long provinceDictId = areaDictGateway.getDictIdByGroupCodeAndItemValue(AreaType.Group.area_type.groupCode(), AreaType.city.itemValue());
		List<AreaDO> areaDOs = iAreaService.getByNameAndTypeDictIdAndParentId(cityName,provinceDictId,provinceId);
        if (CollectionUtil.isEmpty(areaDOs)) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		AreaDO areaDO = areaDOs.iterator().next();
		return SingleResponse.of(AreaAppStructMapping.instance.areaDOToAreaVO(areaDO));
	}
	@Cacheable(cacheNames = {"areaRpcControllerCache_queryCountyByNameAndCityId"})
	@Operation(summary = "根据名称查询区县")
	@Override
	public SingleResponse<AreaVO> queryCountyByNameAndCityId(String countyName, Long cityId) {
		Long cityDictId = areaDictGateway.getDictIdByGroupCodeAndItemValue(AreaType.Group.area_type.groupCode(), AreaType.county.itemValue());
		List<AreaDO> areaDOs = iAreaService.getByNameAndTypeDictIdAndParentId(countyName,cityDictId,cityId);
		if (CollectionUtil.isEmpty(areaDOs)) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		AreaDO areaDO = areaDOs.iterator().next();
		return SingleResponse.of(AreaAppStructMapping.instance.areaDOToAreaVO(areaDO));
	}
}
