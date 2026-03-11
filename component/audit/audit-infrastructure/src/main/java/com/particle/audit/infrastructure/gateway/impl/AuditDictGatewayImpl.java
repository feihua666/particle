package com.particle.audit.infrastructure.gateway.impl;

import com.particle.audit.domain.AuditDictItemInfo;
import com.particle.audit.domain.gateway.AuditDictGateway;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 审核字典防腐层实现
 * </p>
 *
 * @author yangwei
 * @since 2026-01-19 17:10:06
 */
@Component
public class AuditDictGatewayImpl implements AuditDictGateway {

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

	@Override
	public List<AuditDictItemInfo> getDictItemListByGroupCode(String groupCode) {
		MultiResponse<DictVO> dictVOMultiResponse = dictRpcFeignClient.getItemsByGroupCode(groupCode);
		return dictVOMultiResponse.getData().
				stream().map(item ->
						AuditDictItemInfo.create(
								item.getId(),
								item.getCode(),
								item.getName(),
								item.getValue(),
								item.getPrivateFlag(),
								item.getPrivateFlagMemo(),
								item.getGroupFlag(),
								item.getGroupFlagMemo(),
								item.getTags())
								).collect(Collectors.toList());
	}

	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
