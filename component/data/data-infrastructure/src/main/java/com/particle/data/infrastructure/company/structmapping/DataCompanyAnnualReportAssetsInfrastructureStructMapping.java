package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.domain.company.DataCompanyAnnualReportAssets;
import com.particle.data.domain.company.DataCompanyAnnualReportAssetsId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业资产状况信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportAssetsInfrastructureStructMapping {
	public static DataCompanyAnnualReportAssetsInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportAssetsInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportAssetsId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportAssetsId.of(id);
	}
	protected Long map(DataCompanyAnnualReportAssetsId dataCompanyAnnualReportAssetsId){
		if (dataCompanyAnnualReportAssetsId == null) {
			return null;
		}
		return dataCompanyAnnualReportAssetsId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAssetsInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportAssetsDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportAssets dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssets(@MappingTarget DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets,DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAssetsInfrastructureStructMapping#map(DataCompanyAnnualReportAssetsId)}
	 * @param dataCompanyAnnualReportAssets
	 * @return
	 */
	public abstract DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsToDataCompanyAnnualReportAssetsDO(DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets);

}
