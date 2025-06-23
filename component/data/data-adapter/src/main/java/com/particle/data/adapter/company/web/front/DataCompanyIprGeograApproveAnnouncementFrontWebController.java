package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyIprGeograApproveAnnouncementApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权地理标识核准公告前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Tag(name = "企业知识产权地理标识核准公告pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_ipr_geogra_approve_announcement")
public class DataCompanyIprGeograApproveAnnouncementFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyIprGeograApproveAnnouncementApplicationService iDataCompanyIprGeograApproveAnnouncementApplicationService;


}