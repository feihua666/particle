package com.particle.scheduler.infrastructure.gateway.impl;

import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.value.SchedulerDictItemInfo;
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
public class SchedulerDictGatewayImpl implements SchedulerDictGateway {


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

	@Override
	public Long getDictIdByGroupCodeAndItemValue(String groupCode, String value) {
		SingleResponse<DictVO> byGroupCodeAndItemValue = dictRpcFeignClient.getByGroupCodeAndItemValue(groupCode, value);
		if (byGroupCodeAndItemValue.getData() == null) {
			return null;
		}
		return byGroupCodeAndItemValue.getData().getId();
	}

	@Override
	public SchedulerDictItemInfo getSchedulerDictItemInfoById(Long id) {
		SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryById(id);
		DictVO data = dictVOSingleResponse.getData();
		if (data == null) {
			return null;
		}
		return SchedulerDictItemInfo.create(data.getId(), data.getCode(), data.getName(), data.getValue());
	}

	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
