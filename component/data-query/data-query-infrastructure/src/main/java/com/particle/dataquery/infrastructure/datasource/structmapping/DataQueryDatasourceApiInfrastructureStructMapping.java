package com.particle.dataquery.infrastructure.datasource.structmapping;

import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据查询数据源接口 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Mapper
public abstract class DataQueryDatasourceApiInfrastructureStructMapping {
	public static DataQueryDatasourceApiInfrastructureStructMapping instance = Mappers.getMapper( DataQueryDatasourceApiInfrastructureStructMapping.class );

	protected DataQueryDatasourceApiId map(Long id){
		if (id == null) {
			return null;
		}
		return DataQueryDatasourceApiId.of(id);
	}
	protected Long map(DataQueryDatasourceApiId dataQueryDatasourceApiId){
		if (dataQueryDatasourceApiId == null) {
			return null;
		}
		return dataQueryDatasourceApiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDatasourceApiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataQueryDatasourceApiDO
	 * @return
	 */
	public abstract DataQueryDatasourceApi dataQueryDatasourceApiDOToDataQueryDatasourceApi(@MappingTarget DataQueryDatasourceApi dataQueryDatasourceApi,DataQueryDatasourceApiDO dataQueryDatasourceApiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDatasourceApiInfrastructureStructMapping#map(DataQueryDatasourceApiId)}
	 * @param dataQueryDatasourceApi
	 * @return
	 */
	public abstract DataQueryDatasourceApiDO dataQueryDatasourceApiToDataQueryDatasourceApiDO(DataQueryDatasourceApi dataQueryDatasourceApi);

}
