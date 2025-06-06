package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyJudgmentDocumentContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业裁判文书内容后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Tag(name = "企业裁判文书内容移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_judgment_document_content")
public class DataCompanyJudgmentDocumentContentAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyJudgmentDocumentContentApplicationService iDataCompanyJudgmentDocumentContentApplicationService;


}