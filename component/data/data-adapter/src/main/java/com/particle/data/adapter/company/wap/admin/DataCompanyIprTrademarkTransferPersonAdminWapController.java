package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkTransferPersonApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权商标转让人后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Tag(name = "企业知识产权商标转让人wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_ipr_trademark_transfer_person")
public class DataCompanyIprTrademarkTransferPersonAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyIprTrademarkTransferPersonApplicationService iDataCompanyIprTrademarkTransferPersonApplicationService;


}