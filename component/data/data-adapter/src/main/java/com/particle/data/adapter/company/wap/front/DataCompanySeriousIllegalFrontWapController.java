package com.particle.data.adapter.company.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanySeriousIllegalApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业严重违法前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Tag(name = "企业严重违法wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_company_serious_illegal")
public class DataCompanySeriousIllegalFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanySeriousIllegalApplicationService iDataCompanySeriousIllegalApplicationService;


}