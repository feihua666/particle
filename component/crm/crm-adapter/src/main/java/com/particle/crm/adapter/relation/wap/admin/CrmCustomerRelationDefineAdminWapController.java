package com.particle.crm.adapter.relation.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crm.client.relation.api.ICrmCustomerRelationDefineApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户关系定义后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Tag(name = "客户关系定义wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crm_customer_relation_define")
public class CrmCustomerRelationDefineAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrmCustomerRelationDefineApplicationService iCrmCustomerRelationDefineApplicationService;


}