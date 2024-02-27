package com.particle.area.infrastructure.gateway.impl;

import com.particle.area.domain.gateway.AreaDictGateway;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 22:48
 */
@Component
public class AreaDictGatewayImpl implements AreaDictGateway {


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
