package com.particle.openplatform.infrastructure.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典依赖
 * </p>
 *
 * @author yangwei
 * @since 2024-03-19 18:06:55
 */
@Component
public class OpenplatformDictGatewayImpl implements OpenplatformDictGateway {


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
	public Map<Long,String> getItemsByGroupCode(String groupCode) {
		MultiResponse<DictVO> itemsByGroupCode = dictRpcFeignClient.getItemsByGroupCode(groupCode);
		List<DictVO> data = itemsByGroupCode.getData();
		if (CollectionUtil.isNotEmpty(data)) {
			Map<Long,String> stringLongMap = data.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getValue()));
			return stringLongMap;
		}
		return null;
	}

	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
