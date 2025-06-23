package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyIprSoftwareCopyrightApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyIprSoftwareCopyrightRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权软件著作远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Tag(name = "企业知识产权软件著作远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_ipr_software_copyright")
public class DataCompanyIprSoftwareCopyrightRpcController extends AbstractBaseRpcAdapter implements DataCompanyIprSoftwareCopyrightRpcFeignClient  {

	@Autowired
	private IDataCompanyIprSoftwareCopyrightApplicationService iDataCompanyIprSoftwareCopyrightApplicationService;


}