package com.particle.cms.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * cms 字典网关
 * </p>
 *
 * @author yangwei
 * @since 2026/1/14 10:37
 */
public interface CmsDictGateway extends IGateway {

    String getDictValueById(Long typeDictId);

    Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
