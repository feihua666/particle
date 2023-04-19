package com.particle.func.infrastructure.application.structmapping;

import com.particle.func.infrastructure.application.dos.FuncApplicationDO;
import com.particle.func.domain.application.FuncApplication;
import com.particle.func.domain.application.FuncApplicationId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 功能应用 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Mapper
public abstract class FuncApplicationInfrastructureStructMapping {
	public static FuncApplicationInfrastructureStructMapping instance = Mappers.getMapper( FuncApplicationInfrastructureStructMapping.class );

	protected FuncApplicationId map(Long id){
		if (id == null) {
			return null;
		}
		return FuncApplicationId.of(id);
	}
	protected Long map(FuncApplicationId funcApplicationId){
		if (funcApplicationId == null) {
			return null;
		}
		return funcApplicationId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncApplicationInfrastructureStructMapping#map(java.lang.Long)}
	 * @param funcApplicationDO
	 * @return
	 */
	public abstract FuncApplication funcApplicationDOToFuncApplication(@MappingTarget FuncApplication funcApplication,FuncApplicationDO funcApplicationDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncApplicationInfrastructureStructMapping#map(FuncApplicationId)}
	 * @param funcApplication
	 * @return
	 */
	public abstract FuncApplicationDO funcApplicationToFuncApplicationDO(FuncApplication funcApplication);

}
