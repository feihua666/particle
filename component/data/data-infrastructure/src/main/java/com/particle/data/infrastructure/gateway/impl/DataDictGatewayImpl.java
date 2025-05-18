package com.particle.data.infrastructure.gateway.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.common.tool.DataDictItemInfo;
import com.particle.data.domain.gateway.DataDictGateway;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典依赖
 * </p>
 *
 * @author yangwei
 * @since 2025-04-23 17:36:51
 */
@Component
public class DataDictGatewayImpl implements DataDictGateway {


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
	public DataDictItemInfo matchWithMappingValue(String mappingValue, String dictGroupCode,Boolean includeName,Boolean includeValue) {
		if (StrUtil.isEmpty(mappingValue)) {
			return null;
		}
		SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryByMappingValue(mappingValue, dictGroupCode,includeName,includeValue);
		if (dictVOSingleResponse.getData() != null) {
			DictVO dictVO = dictVOSingleResponse.getData();
			return DataDictItemInfo.create(dictVO.getId(),dictVO.getCode(), dictVO.getName(), dictVO.getValue());
		}
		return null;
	}


	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
}
