package com.particle.crm.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crm.adapter.feign.client.company.rpc.CrmDeptRpcFeignClient;
import com.particle.crm.client.company.api.ICrmDeptApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户公司部门远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Tag(name = "客户公司部门远程调用相关接口")
@RestController
@RequestMapping("/rpc/crm_dept")
public class CrmDeptRpcController extends AbstractBaseRpcAdapter implements CrmDeptRpcFeignClient  {

	@Autowired
	private ICrmDeptApplicationService iCrmDeptApplicationService;


}
