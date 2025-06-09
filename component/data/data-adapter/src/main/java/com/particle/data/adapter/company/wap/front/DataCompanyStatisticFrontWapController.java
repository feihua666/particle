package com.particle.data.adapter.company.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyStatisticApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业统计前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Tag(name = "企业统计wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_company_statistic")
public class DataCompanyStatisticFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyStatisticApplicationService iDataCompanyStatisticApplicationService;


}