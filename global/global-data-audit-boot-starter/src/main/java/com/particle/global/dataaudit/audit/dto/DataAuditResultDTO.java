package com.particle.global.dataaudit.audit.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.basic.DTO;
import com.particle.global.tool.diff.DataAuditTool;
import lombok.Data;
import lombok.experimental.Accessors;
import org.javers.core.diff.changetype.PropertyChange;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据审计结果
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 16:53
 */
@Accessors(chain = true)
@Data
public class DataAuditResultDTO extends DTO {
	/**
	 * 字段名称，一般为中文
	 */
	private String name;
	/**
	 * 字段属性
	 */
	private String property;
	/**
	 * 原来值
	 */
	private String oldValue;
	/**
	 * 新值
	 */
	private String newValue;

	/**
	 * 数据id，一般为数据主键
	 */
	private Long dataId;
	/**
	 * 数据对应的表名
	 */
	private String dataTable;

	/**
	 * 数据主体，
	 * 对数据库的：如果是数据实体，可以是数据的实现类名
	 * 对其它 比如 redis 这里可以是数据key等
	 */
	private String dataEntity;
	/**
	 * 变化类型,如添加、删除等 取值 {@link PropertyChange#getChangeType()}
	 */
	private String changeType;
	/**
	 * 操作类型 保持和 {@link OpLog#type()}意义相同
	 */
	private String type;

	public static DataAuditResultDTO create(String name,
											 String property,
											 String oldValue,
											 String newValue,Long dataId,String dataTable,String dataEntity,String changeType, String type) {
		DataAuditResultDTO propertyCompareResult = new DataAuditResultDTO();
		propertyCompareResult.setName(name);
		propertyCompareResult.setProperty(property);
		propertyCompareResult.setOldValue(oldValue);
		propertyCompareResult.setNewValue(newValue);
		propertyCompareResult.setDataId(dataId);
		propertyCompareResult.setDataTable(dataTable);
		propertyCompareResult.setDataEntity(dataEntity);
		propertyCompareResult.setChangeType(changeType);
		propertyCompareResult.setType(type);
		return propertyCompareResult;
	}

	public static DataAuditResultDTO create(DataAuditTool.PropertyCompareResult propertyCompareResult) {
		return DataAuditResultDTO.create(propertyCompareResult.getName(),
				propertyCompareResult.getProperty(),
				propertyCompareResult.getOldValue(),
				propertyCompareResult.getNewValue(),
				null,
				null,
				null,
				propertyCompareResult.getChangeType(),
				null
		);
	}

	public static List<DataAuditResultDTO> create(List<DataAuditTool.PropertyCompareResult> propertyCompareResults) {
		if (CollectionUtil.isEmpty(propertyCompareResults)) {
			return Collections.emptyList();
		}
		return propertyCompareResults.stream().map(item -> create(item)).collect(Collectors.toList());
	}
}
