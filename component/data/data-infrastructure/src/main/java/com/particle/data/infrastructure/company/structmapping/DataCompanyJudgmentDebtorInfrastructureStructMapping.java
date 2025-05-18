package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import com.particle.data.domain.company.DataCompanyJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyJudgmentDebtorId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业被执行人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDebtorInfrastructureStructMapping {
	public static DataCompanyJudgmentDebtorInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDebtorInfrastructureStructMapping.class );

	protected DataCompanyJudgmentDebtorId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyJudgmentDebtorId.of(id);
	}
	protected Long map(DataCompanyJudgmentDebtorId dataCompanyJudgmentDebtorId){
		if (dataCompanyJudgmentDebtorId == null) {
			return null;
		}
		return dataCompanyJudgmentDebtorId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDebtorInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyJudgmentDebtorDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDebtor dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtor(@MappingTarget DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor,DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDebtorInfrastructureStructMapping#map(DataCompanyJudgmentDebtorId)}
	 * @param dataCompanyJudgmentDebtor
	 * @return
	 */
	public abstract DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorToDataCompanyJudgmentDebtorDO(DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor);

}
