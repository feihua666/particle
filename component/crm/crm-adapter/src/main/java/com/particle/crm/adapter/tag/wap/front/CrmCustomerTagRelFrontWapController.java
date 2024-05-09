package com.particle.crm.adapter.tag.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crm.client.tag.api.ICrmCustomerTagRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户标签关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Tag(name = "客户标签关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/crm_customer_tag_rel")
public class CrmCustomerTagRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrmCustomerTagRelApplicationService iCrmCustomerTagRelApplicationService;


}