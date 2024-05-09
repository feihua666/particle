package com.particle.crm.adapter.relation.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.crm.client.relation.api.ICrmCustomerRelationApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户与客户关系前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Tag(name = "客户与客户关系pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/crm_customer_relation")
public class CrmCustomerRelationFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerRelationApplicationService iCrmCustomerRelationApplicationService;


}