package com.particle.dataquery.infrastructure.dataapi.structmapping;

import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据查询数据接口 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Mapper
public abstract class DataQueryDataApiInfrastructureStructMapping {
	public static DataQueryDataApiInfrastructureStructMapping instance = Mappers.getMapper( DataQueryDataApiInfrastructureStructMapping.class );

	protected DataQueryDataApiId map(Long id){
		if (id == null) {
			return null;
		}
		return DataQueryDataApiId.of(id);
	}
	protected Long map(DataQueryDataApiId dataQueryDataApiId){
		if (dataQueryDataApiId == null) {
			return null;
		}
		return dataQueryDataApiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDataApiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataQueryDataApiDO
	 * @return
	 */
	public abstract DataQueryDataApi dataQueryDataApiDOToDataQueryDataApi(@MappingTarget DataQueryDataApi dataQueryDataApi,DataQueryDataApiDO dataQueryDataApiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDataApiInfrastructureStructMapping#map(DataQueryDataApiId)}
	 * @param dataQueryDataApi
	 * @return
	 */
	public abstract DataQueryDataApiDO dataQueryDataApiToDataQueryDataApiDO(DataQueryDataApi dataQueryDataApi);

}
