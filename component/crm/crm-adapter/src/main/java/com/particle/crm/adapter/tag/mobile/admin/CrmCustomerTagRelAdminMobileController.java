package com.particle.crm.adapter.tag.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.crm.client.tag.api.ICrmCustomerTagRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户标签关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Tag(name = "客户标签关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/crm_customer_tag_rel")
public class CrmCustomerTagRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrmCustomerTagRelApplicationService iCrmCustomerTagRelApplicationService;


}
