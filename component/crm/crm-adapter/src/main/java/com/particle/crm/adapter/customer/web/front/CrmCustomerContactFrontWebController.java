package com.particle.crm.adapter.customer.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.crm.client.customer.api.ICrmCustomerContactApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户联系方式前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Tag(name = "客户联系方式pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/crm_customer_contact")
public class CrmCustomerContactFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerContactApplicationService iCrmCustomerContactApplicationService;


}