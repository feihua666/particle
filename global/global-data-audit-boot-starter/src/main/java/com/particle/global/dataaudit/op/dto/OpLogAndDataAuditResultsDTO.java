package com.particle.global.dataaudit.op.dto;

import com.particle.global.dataaudit.audit.dto.DataAuditResultDTO;
import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;
import com.particle.global.dto.basic.DTO;
import com.particle.global.exception.Assert;
import lombok.Data;
import org.javers.common.collections.Lists;

import java.util.List;

/**
 * <p>
 * 操作日志 和 数据审计结果持有对象 主要用来做持久化传输对象
 * 该对象有点和 {@link DataAuditResultWithOpLogDTO} 相同，但两者用途不同，还是独立分开了，最终会转化为本对象做数据持久化的载体
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 17:24
 */
@Data
public class OpLogAndDataAuditResultsDTO extends DTO {

	/**
	 * 操作日志对象数据
	 */
	private OpLogDTO opLogDTO;
	/**
	 * 自定义操作日志对应的审计数据结果
	 */
	private List<DataAuditResultDTO> dataAuditResultDTOS;


	public static OpLogAndDataAuditResultsDTO create(OpLogDTO opLogDTO, List<DataAuditResultDTO> dataAuditResultDTOS) {

		OpLogAndDataAuditResultsDTO dataAuditResultWithOpLogDTO = new OpLogAndDataAuditResultsDTO();
		dataAuditResultWithOpLogDTO.setOpLogDTO(opLogDTO);
		dataAuditResultWithOpLogDTO.setDataAuditResultDTOS(dataAuditResultDTOS);

		return dataAuditResultWithOpLogDTO;
	}
	public static OpLogAndDataAuditResultsDTO create(OpLogDTO opLogDTO, DataAuditResultDTO dataAuditResultDTO) {
		Assert.notNull(opLogDTO,"opLogDTO 不能为空");

		OpLogAndDataAuditResultsDTO dataAuditResultWithOpLogDTO = new OpLogAndDataAuditResultsDTO();
		dataAuditResultWithOpLogDTO.setOpLogDTO(opLogDTO);
		dataAuditResultWithOpLogDTO.setDataAuditResultDTOS(Lists.asList(dataAuditResultDTO));

		return dataAuditResultWithOpLogDTO;
	}
}
