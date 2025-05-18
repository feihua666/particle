package com.particle.data.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.data.common.tool.DataDictItemInfo;

/**
 * <p>
 * 数据模块依赖字典
 * </p>
 *
 * @author yangwei
 * @since 2025-04-23 17:33:44
 */
public interface DataDictGateway extends IGateway {

	/**
	 * 根据字典类型id获取字典值
	 * @param typeDictId
	 * @return
	 */
	String getDictValueById(Long typeDictId);

	/**
	 * 根据字典映射值获取字典项信息
	 * @param mappingValue
	 * @param dictGroupCode
	 * @return
	 */
	DataDictItemInfo matchWithMappingValue(String mappingValue,String dictGroupCode,Boolean includeName,Boolean includeValue);
}
