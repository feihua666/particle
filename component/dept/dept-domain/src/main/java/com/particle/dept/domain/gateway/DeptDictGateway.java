package com.particle.dept.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 部门字典
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 13:58:17
 */
public interface DeptDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
