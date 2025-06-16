package com.particle.data.adapter.company.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPatentPartyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权专利当事人前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Tag(name = "企业知识产权专利当事人wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_company_ipr_patent_party")
public class DataCompanyIprPatentPartyFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyIprPatentPartyApplicationService iDataCompanyIprPatentPartyApplicationService;


}
