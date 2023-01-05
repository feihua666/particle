package com.particle.lowcode.infrastructure.generator.structmapping;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeDatasourceDO;
import com.particle.lowcode.domain.generator.LowcodeDatasource;
import com.particle.lowcode.domain.generator.LowcodeDatasourceId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 低代码数据源 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Mapper
public abstract class LowcodeDatasourceInfrastructureStructMapping {
	public static LowcodeDatasourceInfrastructureStructMapping instance = Mappers.getMapper( LowcodeDatasourceInfrastructureStructMapping.class );

	protected LowcodeDatasourceId map(Long id){
		if (id == null) {
			return null;
		}
		return LowcodeDatasourceId.of(id);
	}
	protected Long map(LowcodeDatasourceId lowcodeDatasourceId){
		if (lowcodeDatasourceId == null) {
			return null;
		}
		return lowcodeDatasourceId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeDatasourceInfrastructureStructMapping#map(java.lang.Long)}
	 * @param lowcodeDatasourceDO
	 * @return
	 */
	public abstract LowcodeDatasource lowcodeDatasourceDOToLowcodeDatasource(@MappingTarget LowcodeDatasource lowcodeDatasource,LowcodeDatasourceDO lowcodeDatasourceDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeDatasourceInfrastructureStructMapping#map(LowcodeDatasourceId)}
	 * @param lowcodeDatasource
	 * @return
	 */
	public abstract LowcodeDatasourceDO lowcodeDatasourceToLowcodeDatasourceDO(LowcodeDatasource lowcodeDatasource);

}
