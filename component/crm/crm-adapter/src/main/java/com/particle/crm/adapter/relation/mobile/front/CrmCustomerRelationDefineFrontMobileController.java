package com.particle.crm.adapter.relation.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.crm.client.relation.api.ICrmCustomerRelationDefineApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户关系定义前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Tag(name = "客户关系定义移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/crm_customer_relation_define")
public class CrmCustomerRelationDefineFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrmCustomerRelationDefineApplicationService iCrmCustomerRelationDefineApplicationService;


}