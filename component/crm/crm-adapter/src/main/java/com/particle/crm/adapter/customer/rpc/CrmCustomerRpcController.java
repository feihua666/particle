package com.particle.crm.adapter.customer.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crm.adapter.feign.client.customer.rpc.CrmCustomerTransRpcFeignClient;
import com.particle.crm.client.customer.api.ICrmCustomerApplicationService;
import com.particle.crm.adapter.feign.client.customer.rpc.CrmCustomerRpcFeignClient;
import com.particle.crm.client.customer.dto.data.CrmCustomerTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 客户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Tag(name = "客户远程调用相关接口")
@RestController
@RequestMapping("/rpc/crm_customer")
public class CrmCustomerRpcController extends AbstractBaseRpcAdapter implements CrmCustomerRpcFeignClient, CrmCustomerTransRpcFeignClient {

	@Autowired
	private ICrmCustomerApplicationService iCrmCustomerApplicationService;

	@Autowired
	private CrmCustomerTransServiceImpl deptTransService;

	@Override
	public boolean supportBatch(String type) {
		return deptTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<CrmCustomerTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return deptTransService.transBatch(type, keys);
	}
}