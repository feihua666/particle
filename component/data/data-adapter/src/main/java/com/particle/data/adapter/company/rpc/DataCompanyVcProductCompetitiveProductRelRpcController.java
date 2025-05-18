package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyVcProductCompetitiveProductRelApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyVcProductCompetitiveProductRelRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业融资产品竞品关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Tag(name = "企业融资产品竞品关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_vc_product_competitive_product_rel")
public class DataCompanyVcProductCompetitiveProductRelRpcController extends AbstractBaseRpcAdapter implements DataCompanyVcProductCompetitiveProductRelRpcFeignClient  {

	@Autowired
	private IDataCompanyVcProductCompetitiveProductRelApplicationService iDataCompanyVcProductCompetitiveProductRelApplicationService;


}