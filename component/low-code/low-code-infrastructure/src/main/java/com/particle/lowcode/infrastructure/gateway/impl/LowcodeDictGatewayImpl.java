package com.particle.lowcode.infrastructure.gateway.impl;

import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.domain.generator.gateway.LowcodeDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典依赖
 * </p>
 *
 * @author yangwei
 * @since 2023-02-15 18:15:07
 */
@Component
public class LowcodeDictGatewayImpl implements LowcodeDictGateway {


	private DictRpcFeignClient dictRpcFeignClient;

	@Override
	public String getDictValueById(Long typeDictId) {
		SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryById(typeDictId);
		if (dictVOSingleResponse.getData() == null) {
			return null;
		}
		return dictVOSingleResponse.getData().getValue();
	}

	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
