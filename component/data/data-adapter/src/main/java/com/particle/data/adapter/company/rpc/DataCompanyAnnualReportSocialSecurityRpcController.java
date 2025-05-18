package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyAnnualReportSocialSecurityApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyAnnualReportSocialSecurityRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业年报社保远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Tag(name = "企业年报社保远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_annual_report_social_security")
public class DataCompanyAnnualReportSocialSecurityRpcController extends AbstractBaseRpcAdapter implements DataCompanyAnnualReportSocialSecurityRpcFeignClient  {

	@Autowired
	private IDataCompanyAnnualReportSocialSecurityApplicationService iDataCompanyAnnualReportSocialSecurityApplicationService;


}