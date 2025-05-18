package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyVcFinancingInvestInstitutionRelApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyVcFinancingInvestInstitutionRelRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业融资历史投资机构关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Tag(name = "企业融资历史投资机构关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_vc_financing_invest_institution_rel")
public class DataCompanyVcFinancingInvestInstitutionRelRpcController extends AbstractBaseRpcAdapter implements DataCompanyVcFinancingInvestInstitutionRelRpcFeignClient  {

	@Autowired
	private IDataCompanyVcFinancingInvestInstitutionRelApplicationService iDataCompanyVcFinancingInvestInstitutionRelApplicationService;


}