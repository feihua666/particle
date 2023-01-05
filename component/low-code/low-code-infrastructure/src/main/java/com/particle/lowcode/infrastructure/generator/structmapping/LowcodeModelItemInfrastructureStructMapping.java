package com.particle.lowcode.infrastructure.generator.structmapping;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.LowcodeModelItemId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 低代码模型项目 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Mapper
public abstract class LowcodeModelItemInfrastructureStructMapping {
	public static LowcodeModelItemInfrastructureStructMapping instance = Mappers.getMapper( LowcodeModelItemInfrastructureStructMapping.class );

	protected LowcodeModelItemId map(Long id){
		if (id == null) {
			return null;
		}
		return LowcodeModelItemId.of(id);
	}
	protected Long map(LowcodeModelItemId lowcodeModelItemId){
		if (lowcodeModelItemId == null) {
			return null;
		}
		return lowcodeModelItemId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeModelItemInfrastructureStructMapping#map(java.lang.Long)}
	 * @param lowcodeModelItemDO
	 * @return
	 */
	public abstract LowcodeModelItem lowcodeModelItemDOToLowcodeModelItem(@MappingTarget LowcodeModelItem lowcodeModelItem,LowcodeModelItemDO lowcodeModelItemDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeModelItemInfrastructureStructMapping#map(LowcodeModelItemId)}
	 * @param lowcodeModelItem
	 * @return
	 */
	public abstract LowcodeModelItemDO lowcodeModelItemToLowcodeModelItemDO(LowcodeModelItem lowcodeModelItem);

}
