package com.particle.workflow.infrastructure.gateway.impl;

import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.domain.gateway.WorkflowDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * workflow 依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2026-05-02 19:12:48
 */
@Component
public class WorkflowDictGatewayImpl implements WorkflowDictGateway {


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
	public String getDictNameById(Long typeDictId) {
		SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryById(typeDictId);
		if (dictVOSingleResponse.getData() == null) {
			return null;
		}
		return dictVOSingleResponse.getData().getName();
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

	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
