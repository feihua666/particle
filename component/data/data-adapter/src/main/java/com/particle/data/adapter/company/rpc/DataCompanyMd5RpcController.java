package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyMd5RpcFeignClient;
import com.particle.data.client.company.api.IDataCompanyMd5ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业md5远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Tag(name = "企业md5远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_md5")
public class DataCompanyMd5RpcController extends AbstractBaseRpcAdapter implements DataCompanyMd5RpcFeignClient  {

	@Autowired
	private IDataCompanyMd5ApplicationService iDataCompanyMd5ApplicationService;


}
