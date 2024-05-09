package com.particle.crm.adapter.tag.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crm.client.tag.api.ICrmCustomerTagRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户标签关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Tag(name = "客户标签关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crm_customer_tag_rel")
public class CrmCustomerTagRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrmCustomerTagRelApplicationService iCrmCustomerTagRelApplicationService;


}