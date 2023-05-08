package com.particle.global.dataaudit.audit;

import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;

import java.util.List;

/**
 * <p>
 * 数据审计 结果处理，一般用来持久化数据
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 15:28
 */
public interface IDataAuditResultHandler {

	/**
	 * 处理对比的结果
	 * @param dataAuditResultWithOpLogDTOS
	 */
	void handle(List<DataAuditResultWithOpLogDTO> dataAuditResultWithOpLogDTOS);
}
