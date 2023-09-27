package com.particle.report.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 报告字典
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 15:10:31
 */
public interface ReportDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);
}
