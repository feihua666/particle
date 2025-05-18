package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanySeriousIllegalApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业严重违法前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Tag(name = "企业严重违法pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_serious_illegal")
public class DataCompanySeriousIllegalFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanySeriousIllegalApplicationService iDataCompanySeriousIllegalApplicationService;


}