package com.particle.func.infrastructure.funcapplicationfuncrel.structmapping;

import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRel;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 功能应用功能关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Mapper
public abstract class FuncApplicationFuncRelInfrastructureStructMapping {
	public static FuncApplicationFuncRelInfrastructureStructMapping instance = Mappers.getMapper( FuncApplicationFuncRelInfrastructureStructMapping.class );

	protected FuncApplicationFuncRelId map(Long id){
		if (id == null) {
			return null;
		}
		return FuncApplicationFuncRelId.of(id);
	}
	protected Long map(FuncApplicationFuncRelId funcApplicationFuncRelId){
		if (funcApplicationFuncRelId == null) {
			return null;
		}
		return funcApplicationFuncRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncApplicationFuncRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param funcApplicationFuncRelDO
	 * @return
	 */
	public abstract FuncApplicationFuncRel funcApplicationFuncRelDOToFuncApplicationFuncRel(@MappingTarget FuncApplicationFuncRel funcApplicationFuncRel,FuncApplicationFuncRelDO funcApplicationFuncRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncApplicationFuncRelInfrastructureStructMapping#map(FuncApplicationFuncRelId)}
	 * @param funcApplicationFuncRel
	 * @return
	 */
	public abstract FuncApplicationFuncRelDO funcApplicationFuncRelToFuncApplicationFuncRelDO(FuncApplicationFuncRel funcApplicationFuncRel);

}
