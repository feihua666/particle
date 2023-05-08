package com.particle.global.dataaudit.audit.dto;

import com.particle.global.dataaudit.audit.DataAuditCollectTool;
import com.particle.global.dataaudit.op.dto.OpLogDTO;
import com.particle.global.dto.basic.DTO;
import com.particle.global.exception.Assert;
import com.particle.global.tool.diff.DataAuditTool;
import lombok.Data;
import org.javers.common.collections.Lists;
import org.javers.core.diff.Change;

import java.util.List;

/**
 * <p>
 * 可以收集带有 {@link OpLogDTO} 的数据审计结果，这可以更方便的自定义处理操作日志与数据审计关系
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 16:59
 */
@Data
public class DataAuditResultWithOpLogDTO extends DTO {

	/**
	 * 自定义操作日志
	 */
	private OpLogDTO opLogDTO;
	/**
	 * 自定义操作日志对应的审计数据结果
	 * 如果 opLogDTO 为 null 则该数据处理方式与 {@link DataAuditCollectTool#collectResult(java.util.List)} 收集结果一致
	 */
	private List<DataAuditResultDTO> dataAuditResultDTOS;

	public static DataAuditResultWithOpLogDTO create(OpLogDTO opLogDTO, List<DataAuditResultDTO> dataAuditResultDTOS) {

		DataAuditResultWithOpLogDTO dataAuditResultWithOpLogDTO = new DataAuditResultWithOpLogDTO();
		dataAuditResultWithOpLogDTO.setOpLogDTO(opLogDTO);
		dataAuditResultWithOpLogDTO.setDataAuditResultDTOS(dataAuditResultDTOS);

		return dataAuditResultWithOpLogDTO;
	}

	public static DataAuditResultWithOpLogDTO create(OpLogDTO opLogDTO, DataAuditResultDTO dataAuditResultDTO) {
		Assert.notNull(dataAuditResultDTO,"dataAuditResultDTO 不能为空");

		DataAuditResultWithOpLogDTO dataAuditResultWithOpLogDTO = new DataAuditResultWithOpLogDTO();
		dataAuditResultWithOpLogDTO.setOpLogDTO(opLogDTO);
		dataAuditResultWithOpLogDTO.setDataAuditResultDTOS(Lists.asList(dataAuditResultDTO));

		return dataAuditResultWithOpLogDTO;
	}


	public static DataAuditResultWithOpLogDTO create(List<Change> changes, Long dataId, String dataTable, String dataEntity,String type) {

		List<DataAuditTool.PropertyCompareResult> propertyCompareResults = DataAuditTool.changesToPropertyCompareResults(changes);
		List<DataAuditResultDTO> dataAuditResultDTOS = DataAuditResultDTO.create(propertyCompareResults);
		// 填充 数据id和表名称
		dataAuditResultDTOS.stream().forEach(item -> item.setDataId(dataId).setDataTable(dataTable).setDataEntity(dataEntity).setType(type));
		DataAuditResultWithOpLogDTO dataAuditResultWithOpLogDTO = DataAuditResultWithOpLogDTO.create(null,dataAuditResultDTOS);
		return dataAuditResultWithOpLogDTO;
	}
}
