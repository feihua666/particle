package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyAbnormalApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业经营异常前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Tag(name = "企业经营异常pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_abnormal")
public class DataCompanyAbnormalFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyAbnormalApplicationService iDataCompanyAbnormalApplicationService;


}