package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典依赖
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 13:58
 */
@Component
public class TenantDictGatewayImpl implements TenantDictGateway {


	private DictRpcFeignClient dictRpcFeignClient;

	@Override
	public String getDictValueById(Long typeDictId) {
		SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryById(typeDictId);
		if (dictVOSingleResponse.getData() == null) {
			return null;
		}
		return dictVOSingleResponse.getData().getValue();
	}

	@Override
	public Long getDictIdByGroupCodeAndItemValue(String groupCode, String value) {
		SingleResponse<DictVO> byGroupCodeAndItemValue = dictRpcFeignClient.getByGroupCodeAndItemValue(groupCode, value);
		if (byGroupCodeAndItemValue.getData() == null) {
			return null;
		}
		return byGroupCodeAndItemValue.getData().getId();
	}
	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
