package com.particle.data.infrastructure.dynamictable.structmapping;

import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.domain.dynamictable.DynamicTable;
import com.particle.data.domain.dynamictable.DynamicTableId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据表格 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicTableInfrastructureStructMapping {
	public static DynamicTableInfrastructureStructMapping instance = Mappers.getMapper( DynamicTableInfrastructureStructMapping.class );

	protected DynamicTableId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicTableId.of(id);
	}
	protected Long map(DynamicTableId dynamicTableId){
		if (dynamicTableId == null) {
			return null;
		}
		return dynamicTableId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicTableDO
	 * @return
	 */
	public abstract DynamicTable dynamicTableDOToDynamicTable(@MappingTarget DynamicTable dynamicTable,DynamicTableDO dynamicTableDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableInfrastructureStructMapping#map(DynamicTableId)}
	 * @param dynamicTable
	 * @return
	 */
	public abstract DynamicTableDO dynamicTableToDynamicTableDO(DynamicTable dynamicTable);

}
