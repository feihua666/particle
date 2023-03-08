package com.particle.dataquery.infrastructure.provider.structmapping;

import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.dataquery.domain.provider.DataQueryProvider;
import com.particle.dataquery.domain.provider.DataQueryProviderId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据查询供应商 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Mapper
public abstract class DataQueryProviderInfrastructureStructMapping {
	public static DataQueryProviderInfrastructureStructMapping instance = Mappers.getMapper( DataQueryProviderInfrastructureStructMapping.class );

	protected DataQueryProviderId map(Long id){
		if (id == null) {
			return null;
		}
		return DataQueryProviderId.of(id);
	}
	protected Long map(DataQueryProviderId dataQueryProviderId){
		if (dataQueryProviderId == null) {
			return null;
		}
		return dataQueryProviderId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryProviderInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataQueryProviderDO
	 * @return
	 */
	public abstract DataQueryProvider dataQueryProviderDOToDataQueryProvider(@MappingTarget DataQueryProvider dataQueryProvider,DataQueryProviderDO dataQueryProviderDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryProviderInfrastructureStructMapping#map(DataQueryProviderId)}
	 * @param dataQueryProvider
	 * @return
	 */
	public abstract DataQueryProviderDO dataQueryProviderToDataQueryProviderDO(DataQueryProvider dataQueryProvider);

}
