package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyStatisticApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyStatisticRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业统计远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Tag(name = "企业统计远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_statistic")
public class DataCompanyStatisticRpcController extends AbstractBaseRpcAdapter implements DataCompanyStatisticRpcFeignClient  {

	@Autowired
	private IDataCompanyStatisticApplicationService iDataCompanyStatisticApplicationService;


}