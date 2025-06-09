package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyAbnormalApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyAbnormalRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业经营异常远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Tag(name = "企业经营异常远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_abnormal")
public class DataCompanyAbnormalRpcController extends AbstractBaseRpcAdapter implements DataCompanyAbnormalRpcFeignClient  {

	@Autowired
	private IDataCompanyAbnormalApplicationService iDataCompanyAbnormalApplicationService;


}