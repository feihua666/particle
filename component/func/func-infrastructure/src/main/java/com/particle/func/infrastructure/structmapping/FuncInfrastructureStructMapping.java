package com.particle.func.infrastructure.structmapping;

import com.particle.func.domain.Func;
import com.particle.func.domain.FuncId;
import com.particle.func.infrastructure.dos.FuncDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 菜单功能 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper
public abstract class FuncInfrastructureStructMapping {
	public static FuncInfrastructureStructMapping instance = Mappers.getMapper( FuncInfrastructureStructMapping.class );

	protected FuncId map(Long id){
		if (id == null) {
			return null;
		}
		return FuncId.of(id);
	}
	protected Long map(FuncId funcId){
		if (funcId == null) {
			return null;
		}
		return funcId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncInfrastructureStructMapping#map(java.lang.Long)}
	 * @param funcDO
	 * @return
	 */
	public abstract Func funcDOToFunc(@MappingTarget Func func,FuncDO funcDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncInfrastructureStructMapping#map(FuncId)}
	 * @param func
	 * @return
	 */
	public abstract FuncDO funcToFuncDO(Func func);

}
