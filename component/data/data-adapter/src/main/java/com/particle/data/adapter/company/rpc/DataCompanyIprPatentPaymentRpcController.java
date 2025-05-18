package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPatentPaymentApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyIprPatentPaymentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权专利缴费信息远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Tag(name = "企业知识产权专利缴费信息远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_ipr_patent_payment")
public class DataCompanyIprPatentPaymentRpcController extends AbstractBaseRpcAdapter implements DataCompanyIprPatentPaymentRpcFeignClient  {

	@Autowired
	private IDataCompanyIprPatentPaymentApplicationService iDataCompanyIprPatentPaymentApplicationService;


}