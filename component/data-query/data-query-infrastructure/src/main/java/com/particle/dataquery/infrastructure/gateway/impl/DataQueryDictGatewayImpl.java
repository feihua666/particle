package com.particle.dataquery.infrastructure.gateway.impl;

import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据查询依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 22:48
 */
@Component
public class DataQueryDictGatewayImpl implements DataQueryDictGateway {


	private DictRpcFeignClient dictRpcFeignClient;

	@Override
	public String getDictValueById(Long typeDictId)  {
		SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryById(typeDictId);
		if (dictVOSingleResponse.getData() == null) {
			return null;
		}
		return dictVOSingleResponse.getData().getValue();
	}

	@Override
	public List<String> getDictValuesByIds(List<Long> ids) {
		MultiResponse<DictVO> dictVOMultiResponse = dictRpcFeignClient.queryByIds(ids);
		List<String> collect = dictVOMultiResponse.getData().stream().map(DictVO::getValue).collect(Collectors.toList());
		return collect;
	}

	@Override
	public Map<Long, String> getMapDictValueByIds(List<Long> ids) {
		MultiResponse<DictVO> dictVOMultiResponse = dictRpcFeignClient.queryByIds(ids);
		Map<Long, String> collect = dictVOMultiResponse.getData().stream().collect(Collectors.toMap(DictVO::getId, DictVO::getValue));
		return collect;
	}

	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
