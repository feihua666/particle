package com.particle.crm.adapter.tag.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crm.adapter.feign.client.tag.rpc.CrmCustomerTagRpcFeignClient;
import com.particle.crm.client.tag.api.ICrmCustomerTagApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户标签远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Tag(name = "客户标签远程调用相关接口")
@RestController
@RequestMapping("/rpc/crm_customer_tag")
public class CrmCustomerTagRpcController extends AbstractBaseRpcAdapter implements CrmCustomerTagRpcFeignClient  {

	@Autowired
	private ICrmCustomerTagApplicationService iCrmCustomerTagApplicationService;


}
