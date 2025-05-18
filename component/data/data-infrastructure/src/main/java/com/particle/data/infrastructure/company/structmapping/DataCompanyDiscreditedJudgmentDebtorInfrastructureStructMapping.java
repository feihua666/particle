package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtorId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业失信被执行人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping {
	public static DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping.class );

	protected DataCompanyDiscreditedJudgmentDebtorId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyDiscreditedJudgmentDebtorId.of(id);
	}
	protected Long map(DataCompanyDiscreditedJudgmentDebtorId dataCompanyDiscreditedJudgmentDebtorId){
		if (dataCompanyDiscreditedJudgmentDebtorId == null) {
			return null;
		}
		return dataCompanyDiscreditedJudgmentDebtorId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyDiscreditedJudgmentDebtorDO
	 * @return
	 */
	public abstract DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtor(@MappingTarget DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor,DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping#map(DataCompanyDiscreditedJudgmentDebtorId)}
	 * @param dataCompanyDiscreditedJudgmentDebtor
	 * @return
	 */
	public abstract DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorToDataCompanyDiscreditedJudgmentDebtorDO(DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor);

}
