package com.particle.crm.adapter.customer.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.crm.client.customer.api.ICrmCustomerContactApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户联系方式前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Tag(name = "客户联系方式移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/crm_customer_contact")
public class CrmCustomerContactFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrmCustomerContactApplicationService iCrmCustomerContactApplicationService;


}