package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import com.particle.data.domain.company.DataCompanyVcProduct;
import com.particle.data.domain.company.DataCompanyVcProductId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业融资产品 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcProductInfrastructureStructMapping {
	public static DataCompanyVcProductInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyVcProductInfrastructureStructMapping.class );

	protected DataCompanyVcProductId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyVcProductId.of(id);
	}
	protected Long map(DataCompanyVcProductId dataCompanyVcProductId){
		if (dataCompanyVcProductId == null) {
			return null;
		}
		return dataCompanyVcProductId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcProductInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyVcProductDO
	 * @return
	 */
	public abstract DataCompanyVcProduct dataCompanyVcProductDOToDataCompanyVcProduct(@MappingTarget DataCompanyVcProduct dataCompanyVcProduct,DataCompanyVcProductDO dataCompanyVcProductDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcProductInfrastructureStructMapping#map(DataCompanyVcProductId)}
	 * @param dataCompanyVcProduct
	 * @return
	 */
	public abstract DataCompanyVcProductDO dataCompanyVcProductToDataCompanyVcProductDO(DataCompanyVcProduct dataCompanyVcProduct);

}
