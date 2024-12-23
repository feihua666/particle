package com.particle.area.infrastructure.structmapping;

import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import com.particle.area.infrastructure.dos.AreaDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 区域 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AreaInfrastructureStructMapping {
	public static AreaInfrastructureStructMapping instance = Mappers.getMapper( AreaInfrastructureStructMapping.class );

	protected AreaId map(Long id){
		if (id == null) {
			return null;
		}
		return AreaId.of(id);
	}
	protected Long map(AreaId areaId){
		if (areaId == null) {
			return null;
		}
		return areaId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AreaInfrastructureStructMapping#map(java.lang.Long)}
	 * @param areaDO
	 * @return
	 */
	public abstract Area areaDOToArea(@MappingTarget Area area,AreaDO areaDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AreaInfrastructureStructMapping#map(AreaId)}
	 * @param area
	 * @return
	 */
	public abstract AreaDO areaToAreaDO(Area area);

}
