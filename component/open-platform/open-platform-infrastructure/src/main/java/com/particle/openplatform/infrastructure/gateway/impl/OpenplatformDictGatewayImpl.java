package com.particle.openplatform.infrastructure.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemDTO;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemParam;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public Long getDictIdByGroupCodeAndItemValue(String groupCode, String value) {
        SingleResponse<DictVO> byGroupCodeAndItemValue = dictRpcFeignClient.getByGroupCodeAndItemValue(groupCode, value);
        if (byGroupCodeAndItemValue.getData() == null) {
            return null;
        }
        return byGroupCodeAndItemValue.getData().getId();
    }

    @Override
    public Long getIdByCode(String code) {
        SingleResponse<DictVO> dictVOSingleResponse = dictRpcFeignClient.queryByCode(code);
        return Optional.ofNullable(dictVOSingleResponse)
                .map(response -> response.getData())
                .map(DictVO::getId)
                .orElse(null);
    }

    @Override
    public Map<Long, String> getItemsByGroupCode(String groupCode) {
        MultiResponse<DictVO> itemsByGroupCode = dictRpcFeignClient.getItemsByGroupCode(groupCode);
        List<DictVO> data = itemsByGroupCode.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            Map<Long, String> stringLongMap = data.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getValue()));
            return stringLongMap;
        }
        return null;
    }

    @Override
    public Map<Long, List<OpenplatformDocParamFieldDictItemDTO>> getItemsByGroupIds(List<OpenplatformDocParamFieldDictItemParam> paramFieldDictItemParams) {
        Map<Long, String> paramMap = paramFieldDictItemParams.stream()
                // Collectors.toMap 在 item value为null时会报空指针异常，这里注释一下，备忘，使用下载的方式
                // .collect(Collectors.toMap(OpenplatformDocParamFieldDictItemParam::getDictGroupId, item->item.getTags()));
        .collect(HashMap::new, (map, item) -> map.put(item.getDictGroupId(), item.getTags()),
                HashMap::putAll);
        List<Long> groupIds = paramMap.keySet().stream().collect(Collectors.toList());

        SingleResponse<Map<Long, List<DictVO>>> itemsByGroupIds = dictRpcFeignClient.getItemsByGroupIds(groupIds);
        Map<Long, List<DictVO>> data = itemsByGroupIds.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            Map<Long, List<OpenplatformDocParamFieldDictItemDTO>> result = new HashMap<>();
            for (Map.Entry<Long, List<DictVO>> longListEntry : data.entrySet()) {
                List<DictVO> dictVOList = longListEntry.getValue();
                Long dictGroupId = longListEntry.getKey();
                List<OpenplatformDocParamFieldDictItemDTO> dictItemDTOList = dictVOList.stream()
                        .filter(item -> {
                            // 根据标签过滤
                            String tags = paramMap.get(dictGroupId);
                            if (StrUtil.isEmpty(tags)) {
                                return true;
                            }

                            // 字典标签为空，直接过滤掉，不显示
                            if (StrUtil.isEmpty(item.getTags())) {
                                return false;
                            }
                            return Lists.newArrayList(item.getTags().split(",")).stream().anyMatch(tag -> tags.contains(tag));
                        })
                        .map(item -> OpenplatformDocParamFieldDictItemDTO.create(item.getName(), item.getValue()))
						.collect(Collectors.toList());
                result.put(dictGroupId, dictItemDTOList);
            }
			return result;
        }
        return null;
    }

    @Autowired
    public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
        this.dictRpcFeignClient = dictRpcFeignClient;
    }
}
