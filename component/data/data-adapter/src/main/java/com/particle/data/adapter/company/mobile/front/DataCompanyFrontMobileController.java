package com.particle.data.adapter.company.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.company.api.IDataCompanyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Tag(name = "企业移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_company")
public class DataCompanyFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyApplicationService iDataCompanyApplicationService;


}