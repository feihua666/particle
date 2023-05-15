package com.particle.func.infrastructure.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.func.domain.gateway.FuncDictGateway;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
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
 * @since 2022-12-06 18:15
 */
@Component
public class FuncDictGatewayImpl implements FuncDictGateway {


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
