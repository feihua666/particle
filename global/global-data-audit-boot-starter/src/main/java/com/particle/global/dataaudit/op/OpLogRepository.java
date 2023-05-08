package com.particle.global.dataaudit.op;

import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;

import java.util.List;

/**
 * <p>
 * 操作日志持久化接口
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 15:44
 */
public interface OpLogRepository {

	/**
	 * 保存操作日志
	 * 保存要求：数据审计数据都必须有一个操作日志归属对象，这保持了父子关系，如果没有数据审计数据，只持久化操作日志即可！
	 * @param opLogAndDataAuditResultsDTOList
	 */
	void save(List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList);
}
