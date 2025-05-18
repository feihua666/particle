package com.particle.global.openapi.api.portal.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 供应商执行示例
 * </p>
 *
 * @author yangwei
 * @since 2023-08-22 17:31
 */
public class DemoOpenapiExecuteProvider implements OpenapiExecuteProvider {

	public static final String test_provider_code = "test_provider_code";

	@Override
	public boolean supportApi(String apiCode,String apiVersion) {
		return "test_provider_demo".equals(apiCode) && StrUtil.isEmpty(apiVersion);
	}

	@Override
	public boolean supportProvider(String providerCode) {
		return test_provider_code.equals(providerCode);
	}

	@Override
	public SingleResponse<Map<String, Object>> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("param", openapiCommand.getParam());
		paramMap.put("queryString", openapiCommand.getQueryString());
		OpenapiCollectProviderDTO openapiCollectProviderDTO = OpenapiCollectProviderDTO.create(LocalDateTime.now(),LocalDateTime.now(),"/test_provider_demo_none",
				50,
				true,
				200, null, paramMap,null, paramMap, test_provider_code,false);
		openapiContext.addProviderDTO(openapiCollectProviderDTO);
		return SingleResponse.of(paramMap);
	}
}
