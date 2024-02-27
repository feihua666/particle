package com.particle.area.adapter.web.front;

import com.particle.area.client.api.representation.IAreaRepresentationApplicationService;
import com.particle.area.client.constants.AreaConstants;
import com.particle.area.client.dto.command.representation.AreaItemsQueryCommand;
import com.particle.area.client.dto.command.representation.AreaItemsQueryListCommonCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.enums.AreaType;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 区域前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Tag(name = "区域pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/area")
public class AreaFrontWebController extends AbstractBaseWebAdapter {

	/**
	 * 缓存一下中国的区域id
	 */
	private static Long chinaAreaId = null;


	@Autowired
	private IAreaRepresentationApplicationService iAreaRepresentationApplicationService;

	@Autowired
	private IAreaService iAreaService;

	@Operation(summary = "根据类型查询区域通用")
	@GetMapping("/items")
	public MultiResponse<AreaVO> queryItems(AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand){
		return iAreaRepresentationApplicationService.queryItems(areaItemsQueryListCommonCommand);
	}

	@Operation(summary = "查询国家")
	@GetMapping("/country")
	public MultiResponse<AreaVO> queryCountry(){
		AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand = new AreaItemsQueryListCommonCommand();
		areaItemsQueryListCommonCommand.setTypeDictValue(AreaType.country.itemValue());
		return iAreaRepresentationApplicationService.queryItems(areaItemsQueryListCommonCommand);
	}

	@Operation(summary = "查询省")
	@GetMapping("/province")
	public MultiResponse<AreaVO> queryProvince(@Valid AreaItemsQueryCommand areaItemsQueryCommand){
		AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand = new AreaItemsQueryListCommonCommand();
		areaItemsQueryListCommonCommand.setTypeDictValue(AreaType.province.itemValue());
		areaItemsQueryListCommonCommand.setParentId(areaItemsQueryCommand.getParentId());
		return iAreaRepresentationApplicationService.queryItems(areaItemsQueryListCommonCommand);
	}
	@Operation(summary = "查询中国省")
	@GetMapping("/chinaProvince")
	public MultiResponse<AreaVO> queryChinaProvince(){
		if (chinaAreaId == null) {
			AreaDO oneByColumn = iAreaService.getOneByColumn(AreaConstants.AREA_CHINA_CODE, AreaDO::getCode);
			chinaAreaId = oneByColumn.getId();
		}
		AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand = new AreaItemsQueryListCommonCommand();
		areaItemsQueryListCommonCommand.setTypeDictValue(AreaType.province.itemValue());
		areaItemsQueryListCommonCommand.setParentId(chinaAreaId);
		return iAreaRepresentationApplicationService.queryItems(areaItemsQueryListCommonCommand);
	}
	@Operation(summary = "查询市")
	@GetMapping("/city")
	public MultiResponse<AreaVO> queryCity(@Valid AreaItemsQueryCommand areaItemsQueryCommand){
		AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand = new AreaItemsQueryListCommonCommand();
		areaItemsQueryListCommonCommand.setTypeDictValue(AreaType.city.itemValue());
		areaItemsQueryListCommonCommand.setParentId(areaItemsQueryCommand.getParentId());
		return iAreaRepresentationApplicationService.queryItems(areaItemsQueryListCommonCommand);
	}

	@Operation(summary = "查询区县")
	@GetMapping("/county")
	public MultiResponse<AreaVO> queryCounty(@Valid AreaItemsQueryCommand areaItemsQueryCommand){
		AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand = new AreaItemsQueryListCommonCommand();
		areaItemsQueryListCommonCommand.setTypeDictValue(AreaType.county.itemValue());
		areaItemsQueryListCommonCommand.setParentId(areaItemsQueryCommand.getParentId());
		return iAreaRepresentationApplicationService.queryItems(areaItemsQueryListCommonCommand);
	}
}