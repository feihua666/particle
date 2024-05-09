package com.particle.crm.adapter.relation.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crm.client.relation.api.ICrmCustomerRelationDefineApplicationService;
import com.particle.crm.adapter.feign.client.relation.rpc.CrmCustomerRelationDefineRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户关系定义远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Tag(name = "客户关系定义远程调用相关接口")
@RestController
@RequestMapping("/rpc/crm_customer_relation_define")
public class CrmCustomerRelationDefineRpcController extends AbstractBaseRpcAdapter implements CrmCustomerRelationDefineRpcFeignClient  {

	@Autowired
	private ICrmCustomerRelationDefineApplicationService iCrmCustomerRelationDefineApplicationService;


}