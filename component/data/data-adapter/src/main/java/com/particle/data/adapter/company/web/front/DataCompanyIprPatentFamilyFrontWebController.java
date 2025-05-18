package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPatentFamilyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权专利同族信息前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Tag(name = "企业知识产权专利同族信息pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_ipr_patent_family")
public class DataCompanyIprPatentFamilyFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyIprPatentFamilyApplicationService iDataCompanyIprPatentFamilyApplicationService;


}