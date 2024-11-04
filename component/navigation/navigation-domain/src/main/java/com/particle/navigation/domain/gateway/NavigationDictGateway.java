package com.particle.navigation.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 导航依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2024-11-03 15:46:36
 */
public interface NavigationDictGateway extends IGateway {

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
