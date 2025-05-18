package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.domain.company.DataCompanyJudgmentDocument;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业裁判文书 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDocumentInfrastructureStructMapping {
	public static DataCompanyJudgmentDocumentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentInfrastructureStructMapping.class );

	protected DataCompanyJudgmentDocumentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyJudgmentDocumentId.of(id);
	}
	protected Long map(DataCompanyJudgmentDocumentId dataCompanyJudgmentDocumentId){
		if (dataCompanyJudgmentDocumentId == null) {
			return null;
		}
		return dataCompanyJudgmentDocumentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyJudgmentDocumentDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDocument dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocument(@MappingTarget DataCompanyJudgmentDocument dataCompanyJudgmentDocument,DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentInfrastructureStructMapping#map(DataCompanyJudgmentDocumentId)}
	 * @param dataCompanyJudgmentDocument
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentToDataCompanyJudgmentDocumentDO(DataCompanyJudgmentDocument dataCompanyJudgmentDocument);

}
