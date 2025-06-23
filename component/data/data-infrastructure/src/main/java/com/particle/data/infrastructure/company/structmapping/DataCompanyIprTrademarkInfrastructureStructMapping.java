package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.domain.company.DataCompanyIprTrademark;
import com.particle.data.domain.company.DataCompanyIprTrademarkId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkInfrastructureStructMapping {
	public static DataCompanyIprTrademarkInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkId dataCompanyIprTrademarkId){
		if (dataCompanyIprTrademarkId == null) {
			return null;
		}
		return dataCompanyIprTrademarkId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkDO
	 * @return
	 */
	public abstract DataCompanyIprTrademark dataCompanyIprTrademarkDOToDataCompanyIprTrademark(@MappingTarget DataCompanyIprTrademark dataCompanyIprTrademark,DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkInfrastructureStructMapping#map(DataCompanyIprTrademarkId)}
	 * @param dataCompanyIprTrademark
	 * @return
	 */
	public abstract DataCompanyIprTrademarkDO dataCompanyIprTrademarkToDataCompanyIprTrademarkDO(DataCompanyIprTrademark dataCompanyIprTrademark);

}
