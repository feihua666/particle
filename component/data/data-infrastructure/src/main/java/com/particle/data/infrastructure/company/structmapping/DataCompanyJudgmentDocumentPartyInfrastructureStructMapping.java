package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentParty;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业裁判文书当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDocumentPartyInfrastructureStructMapping {
	public static DataCompanyJudgmentDocumentPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentPartyInfrastructureStructMapping.class );

	protected DataCompanyJudgmentDocumentPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyJudgmentDocumentPartyId.of(id);
	}
	protected Long map(DataCompanyJudgmentDocumentPartyId dataCompanyJudgmentDocumentPartyId){
		if (dataCompanyJudgmentDocumentPartyId == null) {
			return null;
		}
		return dataCompanyJudgmentDocumentPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyJudgmentDocumentPartyDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentParty(@MappingTarget DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty,DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentPartyInfrastructureStructMapping#map(DataCompanyJudgmentDocumentPartyId)}
	 * @param dataCompanyJudgmentDocumentParty
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyToDataCompanyJudgmentDocumentPartyDO(DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty);

}
