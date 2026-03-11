package com.particle.audit.domain.gateway;

import com.particle.audit.domain.AuditDictItemInfo;
import com.particle.common.domain.gateway.IGateway;

import java.util.List;

/**
 * <p>
 * 审核字典
 * </p>
 *
 * @author yangwei
 * @since 2026-01-19 17:07:03
 */
public interface AuditDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);

	List<AuditDictItemInfo> getDictItemListByGroupCode(String groupCode);
}
