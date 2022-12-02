package com.particle.func.infrastructure.structmapping;

import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 功能组 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Mapper
public abstract class FuncGroupInfrastructureStructMapping {
	public static FuncGroupInfrastructureStructMapping instance = Mappers.getMapper( FuncGroupInfrastructureStructMapping.class );

	protected FuncGroupId map(Long id){
		if (id == null) {
			return null;
		}
		return FuncGroupId.of(id);
	}
	protected Long map(FuncGroupId funcGroupId){
		if (funcGroupId == null) {
			return null;
		}
		return funcGroupId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncGroupInfrastructureStructMapping#map(java.lang.Long)}
	 * @param funcGroupDO
	 * @return
	 */
	public abstract FuncGroup funcGroupDOToFuncGroup(@MappingTarget FuncGroup funcGroup,FuncGroupDO funcGroupDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncGroupInfrastructureStructMapping#map(FuncGroupId)}
	 * @param funcGroup
	 * @return
	 */
	public abstract FuncGroupDO funcGroupToFuncGroupDO(FuncGroup funcGroup);

}
