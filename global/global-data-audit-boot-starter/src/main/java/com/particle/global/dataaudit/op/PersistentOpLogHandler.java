package com.particle.global.dataaudit.op;

import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;
import com.particle.global.dataaudit.op.dto.OpLogDTO;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 持久化操作日志
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 15:43:26
 */
@Slf4j
public class PersistentOpLogHandler implements IOpLogHandler{

	private OpLogRepository opLogRepository;

	@Override
	public void handle(List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList) {
		if (opLogRepository != null) {
			opLogRepository.save(opLogAndDataAuditResultsDTOList);
		}else {
			log.warn("opLogRepository is null,you can put a spring bean type of OpLogRepository to persist oplog data");
		}
	}

	@Autowired(required = false)
	public void setOpLogRepository(OpLogRepository opLogRepository) {
		this.opLogRepository = opLogRepository;
	}
}
