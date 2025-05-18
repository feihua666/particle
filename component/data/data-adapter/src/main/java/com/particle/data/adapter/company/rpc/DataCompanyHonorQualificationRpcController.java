package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyHonorQualificationApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyHonorQualificationRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业荣誉资质远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Tag(name = "企业荣誉资质远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_honor_qualification")
public class DataCompanyHonorQualificationRpcController extends AbstractBaseRpcAdapter implements DataCompanyHonorQualificationRpcFeignClient  {

	@Autowired
	private IDataCompanyHonorQualificationApplicationService iDataCompanyHonorQualificationApplicationService;


}