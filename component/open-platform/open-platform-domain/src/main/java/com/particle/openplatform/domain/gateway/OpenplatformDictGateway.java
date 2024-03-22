package com.particle.openplatform.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemDTO;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 开放接口字典
 * </p>
 *
 * @author yangwei
 * @since 2024-03-19 18:05:33
 */
public interface OpenplatformDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	Long getIdByCode(String code);
	/**
	 * key为id，value为value
	 * @param groupCode
	 * @return
	 */
	Map<Long, String> getItemsByGroupCode(String groupCode);

	/**
	 * key为字典组id，value为字典项
	 * @param paramFieldDictItemParams
	 * @return
	 */
	Map<Long, List<OpenplatformDocParamFieldDictItemDTO>> getItemsByGroupIds(List<OpenplatformDocParamFieldDictItemParam> paramFieldDictItemParams);


}
