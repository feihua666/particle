package com.particle.data.adapter.company.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyCourtAnnouncementContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业法院公告内容前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Tag(name = "企业法院公告内容wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_company_court_announcement_content")
public class DataCompanyCourtAnnouncementContentFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyCourtAnnouncementContentApplicationService iDataCompanyCourtAnnouncementContentApplicationService;


}