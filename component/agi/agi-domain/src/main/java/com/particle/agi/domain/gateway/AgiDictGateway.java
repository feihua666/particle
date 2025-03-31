package com.particle.agi.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * agi依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2025-01-10 09:34:22
 */
public interface AgiDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	List<String> getDictValuesByIds(List<Long> ids);

	/**
	 * 返回一对一的 id>value
	 * @param ids
	 * @return
	 */
	Map<Long,String> getMapDictValueByIds(List<Long> ids);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
