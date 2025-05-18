package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyJudgmentDebtorApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyJudgmentDebtorRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业被执行人远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Tag(name = "企业被执行人远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_judgment_debtor")
public class DataCompanyJudgmentDebtorRpcController extends AbstractBaseRpcAdapter implements DataCompanyJudgmentDebtorRpcFeignClient  {

	@Autowired
	private IDataCompanyJudgmentDebtorApplicationService iDataCompanyJudgmentDebtorApplicationService;


}