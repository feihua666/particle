package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业裁判文书内容后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Tag(name = "企业裁判文书内容wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_judgment_document_content")
public class DataCompanyJudgmentDocumentContentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyJudgmentDocumentContentApplicationService iDataCompanyJudgmentDocumentContentApplicationService;


}