package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyPrimeStaffPositionApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyPrimeStaffPositionRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业主要人员职位远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Tag(name = "企业主要人员职位远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_prime_staff_position")
public class DataCompanyPrimeStaffPositionRpcController extends AbstractBaseRpcAdapter implements DataCompanyPrimeStaffPositionRpcFeignClient  {

	@Autowired
	private IDataCompanyPrimeStaffPositionApplicationService iDataCompanyPrimeStaffPositionApplicationService;


}