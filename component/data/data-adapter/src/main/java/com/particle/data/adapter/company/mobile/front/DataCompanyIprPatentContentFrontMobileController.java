package com.particle.data.adapter.company.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPatentContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权专利内容前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Tag(name = "企业知识产权专利内容移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_company_ipr_patent_content")
public class DataCompanyIprPatentContentFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyIprPatentContentApplicationService iDataCompanyIprPatentContentApplicationService;


}