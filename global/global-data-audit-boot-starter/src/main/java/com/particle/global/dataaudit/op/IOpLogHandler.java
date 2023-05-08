package com.particle.global.dataaudit.op;

import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;

import java.util.List;

/**
 * <p>
 * 在收集完成操作日志后，调用处理器处理
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 20:57
 */
public interface IOpLogHandler {

	/**
	 * 处理准备好的日志，一般进行持久化处理
	 * @param opLogAndDataAuditResultsDTOList
	 */
	void handle(List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList);
}
