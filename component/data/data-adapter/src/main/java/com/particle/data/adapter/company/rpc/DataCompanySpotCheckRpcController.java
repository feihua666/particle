package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanySpotCheckApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanySpotCheckRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业抽查检查远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Tag(name = "企业抽查检查远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_spot_check")
public class DataCompanySpotCheckRpcController extends AbstractBaseRpcAdapter implements DataCompanySpotCheckRpcFeignClient  {

	@Autowired
	private IDataCompanySpotCheckApplicationService iDataCompanySpotCheckApplicationService;


}