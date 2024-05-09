package com.particle.crm.adapter.customer.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crm.client.customer.api.ICrmCustomerContactApplicationService;
import com.particle.crm.adapter.feign.client.customer.rpc.CrmCustomerContactRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户联系方式远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Tag(name = "客户联系方式远程调用相关接口")
@RestController
@RequestMapping("/rpc/crm_customer_contact")
public class CrmCustomerContactRpcController extends AbstractBaseRpcAdapter implements CrmCustomerContactRpcFeignClient  {

	@Autowired
	private ICrmCustomerContactApplicationService iCrmCustomerContactApplicationService;


}