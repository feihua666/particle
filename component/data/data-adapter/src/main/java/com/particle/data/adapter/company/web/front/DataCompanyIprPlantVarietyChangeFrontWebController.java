package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPlantVarietyChangeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权植物新品种变更信息前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Tag(name = "企业知识产权植物新品种变更信息pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_ipr_plant_variety_change")
public class DataCompanyIprPlantVarietyChangeFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyIprPlantVarietyChangeApplicationService iDataCompanyIprPlantVarietyChangeApplicationService;


}