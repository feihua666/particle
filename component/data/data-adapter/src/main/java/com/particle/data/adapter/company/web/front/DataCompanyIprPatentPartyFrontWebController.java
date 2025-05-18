package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPatentPartyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权当事人前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Tag(name = "企业知识产权当事人pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_ipr_patent_party")
public class DataCompanyIprPatentPartyFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyIprPatentPartyApplicationService iDataCompanyIprPatentPartyApplicationService;


}