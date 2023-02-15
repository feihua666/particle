package com.particle.lowcode.infrastructure.generator.structmapping;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentGenDO;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 低代码生成 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Mapper
public abstract class LowcodeSegmentGenInfrastructureStructMapping {
	public static LowcodeSegmentGenInfrastructureStructMapping instance = Mappers.getMapper( LowcodeSegmentGenInfrastructureStructMapping.class );

	protected LowcodeSegmentGenId map(Long id){
		if (id == null) {
			return null;
		}
		return LowcodeSegmentGenId.of(id);
	}
	protected Long map(LowcodeSegmentGenId lowcodeSegmentGenId){
		if (lowcodeSegmentGenId == null) {
			return null;
		}
		return lowcodeSegmentGenId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeSegmentGenInfrastructureStructMapping#map(java.lang.Long)}
	 * @param lowcodeSegmentGenDO
	 * @return
	 */
	public abstract LowcodeSegmentGen lowcodeSegmentGenDOToLowcodeSegmentGen(@MappingTarget LowcodeSegmentGen lowcodeSegmentGen,LowcodeSegmentGenDO lowcodeSegmentGenDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeSegmentGenInfrastructureStructMapping#map(LowcodeSegmentGenId)}
	 * @param lowcodeSegmentGen
	 * @return
	 */
	public abstract LowcodeSegmentGenDO lowcodeSegmentGenToLowcodeSegmentGenDO(LowcodeSegmentGen lowcodeSegmentGen);

}
