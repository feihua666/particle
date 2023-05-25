package com.particle.message.infrastructure.gateway.impl;

import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.domain.gateway.MessageDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息模板字典依赖
 * </p>
 *
 * @author yangwei
 * @since 2023-05-18 17:27
 */
@Component
public class MessageDictGatewayImpl implements MessageDictGateway {

	private DictRpcFeignClient dictRpcFeignClient;

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
