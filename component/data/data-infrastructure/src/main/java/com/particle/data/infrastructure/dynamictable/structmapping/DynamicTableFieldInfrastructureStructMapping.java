package com.particle.data.infrastructure.dynamictable.structmapping;

import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.domain.dynamictable.DynamicTableField;
import com.particle.data.domain.dynamictable.DynamicTableFieldId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据表格字段 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicTableFieldInfrastructureStructMapping {
	public static DynamicTableFieldInfrastructureStructMapping instance = Mappers.getMapper( DynamicTableFieldInfrastructureStructMapping.class );

	protected DynamicTableFieldId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicTableFieldId.of(id);
	}
	protected Long map(DynamicTableFieldId dynamicTableFieldId){
		if (dynamicTableFieldId == null) {
			return null;
		}
		return dynamicTableFieldId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableFieldInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicTableFieldDO
	 * @return
	 */
	public abstract DynamicTableField dynamicTableFieldDOToDynamicTableField(@MappingTarget DynamicTableField dynamicTableField,DynamicTableFieldDO dynamicTableFieldDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableFieldInfrastructureStructMapping#map(DynamicTableFieldId)}
	 * @param dynamicTableField
	 * @return
	 */
	public abstract DynamicTableFieldDO dynamicTableFieldToDynamicTableFieldDO(DynamicTableField dynamicTableField);

}
