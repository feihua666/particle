package com.particle.crawler.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * crawler 依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2026-05-13 15:20:45
 */
public interface CrawlerDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);
	String getDictNameById(Long typeDictId);
	List<String> getDictValuesByIds(List<Long> ids);

	/**
	 * 返回一对一的 id>value
	 * @param ids
	 * @return
	 */
	Map<Long,String> getMapDictValueByIds(List<Long> ids);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
