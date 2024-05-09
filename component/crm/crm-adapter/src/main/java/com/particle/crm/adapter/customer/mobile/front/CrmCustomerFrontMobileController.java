package com.particle.crm.adapter.customer.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.crm.client.customer.api.ICrmCustomerApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Tag(name = "客户移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/crm_customer")
public class CrmCustomerFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrmCustomerApplicationService iCrmCustomerApplicationService;


}