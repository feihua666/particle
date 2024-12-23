package com.particle.global.dataaudit.op;

import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * 打印操作日志
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 21:10
 */
@Slf4j
public class LoggingOpLogHandler implements IOpLogHandler{
	@Override
	public void handle(List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList) {
		log.info("opLog={}", JsonTool.toJsonStr(opLogAndDataAuditResultsDTOList));
	}
}
