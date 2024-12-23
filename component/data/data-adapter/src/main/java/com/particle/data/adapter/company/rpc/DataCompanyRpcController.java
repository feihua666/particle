package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyRpcFeignClient;
import com.particle.data.client.company.api.IDataCompanyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Tag(name = "企业远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company")
public class DataCompanyRpcController extends AbstractBaseRpcAdapter implements DataCompanyRpcFeignClient  {

	@Autowired
	private IDataCompanyApplicationService iDataCompanyApplicationService;


}
