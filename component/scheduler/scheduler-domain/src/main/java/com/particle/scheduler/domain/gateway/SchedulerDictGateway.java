package com.particle.scheduler.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 任务计划依赖的字典
 * </p>
 *
 * @author yangwei
 * @since 2024-08-28 16:26:36
 */
public interface SchedulerDictGateway extends IGateway {

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
