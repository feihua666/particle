package com.particle.dataquery.infrastructure.datasource.structmapping;

import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据查询数据源 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataQueryDatasourceInfrastructureStructMapping {
	public static DataQueryDatasourceInfrastructureStructMapping instance = Mappers.getMapper( DataQueryDatasourceInfrastructureStructMapping.class );

	protected DataQueryDatasourceId map(Long id){
		if (id == null) {
			return null;
		}
		return DataQueryDatasourceId.of(id);
	}
	protected Long map(DataQueryDatasourceId dataQueryDatasourceId){
		if (dataQueryDatasourceId == null) {
			return null;
		}
		return dataQueryDatasourceId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDatasourceInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataQueryDatasourceDO
	 * @return
	 */
	public abstract DataQueryDatasource dataQueryDatasourceDOToDataQueryDatasource(@MappingTarget DataQueryDatasource dataQueryDatasource,DataQueryDatasourceDO dataQueryDatasourceDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDatasourceInfrastructureStructMapping#map(DataQueryDatasourceId)}
	 * @param dataQueryDatasource
	 * @return
	 */
	public abstract DataQueryDatasourceDO dataQueryDatasourceToDataQueryDatasourceDO(DataQueryDatasource dataQueryDatasource);

}
