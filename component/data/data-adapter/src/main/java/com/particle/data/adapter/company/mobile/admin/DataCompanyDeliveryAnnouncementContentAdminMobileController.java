package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业送达公告内容后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Tag(name = "企业送达公告内容移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_delivery_announcement_content")
public class DataCompanyDeliveryAnnouncementContentAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyDeliveryAnnouncementContentApplicationService iDataCompanyDeliveryAnnouncementContentApplicationService;


}