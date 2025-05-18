package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContent;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业裁判文书内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDocumentContentInfrastructureStructMapping {
	public static DataCompanyJudgmentDocumentContentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentContentInfrastructureStructMapping.class );

	protected DataCompanyJudgmentDocumentContentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyJudgmentDocumentContentId.of(id);
	}
	protected Long map(DataCompanyJudgmentDocumentContentId dataCompanyJudgmentDocumentContentId){
		if (dataCompanyJudgmentDocumentContentId == null) {
			return null;
		}
		return dataCompanyJudgmentDocumentContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyJudgmentDocumentContentDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContent(@MappingTarget DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent,DataCompanyJudgmentDocumentContentDO dataCompanyJudgmentDocumentContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentContentInfrastructureStructMapping#map(DataCompanyJudgmentDocumentContentId)}
	 * @param dataCompanyJudgmentDocumentContent
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentContentDO dataCompanyJudgmentDocumentContentToDataCompanyJudgmentDocumentContentDO(DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent);

}
