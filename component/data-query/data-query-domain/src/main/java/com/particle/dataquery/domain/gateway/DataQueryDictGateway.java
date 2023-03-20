package com.particle.dataquery.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据查询依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 22:47:30
 */
public interface DataQueryDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	List<String> getDictValuesByIds(List<Long> ids);

	/**
	 * 返回一对一的 id>value
	 * @param ids
	 * @return
	 */
	Map<Long,String> getMapDictValueByIds(List<Long> ids);
}
