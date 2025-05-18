package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业开庭公告内容前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Tag(name = "企业开庭公告内容pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_open_court_announcement_content")
public class DataCompanyOpenCourtAnnouncementContentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyOpenCourtAnnouncementContentApplicationService iDataCompanyOpenCourtAnnouncementContentApplicationService;


}