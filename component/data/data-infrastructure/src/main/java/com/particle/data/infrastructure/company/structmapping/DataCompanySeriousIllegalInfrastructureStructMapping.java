package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.data.domain.company.DataCompanySeriousIllegal;
import com.particle.data.domain.company.DataCompanySeriousIllegalId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业严重违法 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanySeriousIllegalInfrastructureStructMapping {
	public static DataCompanySeriousIllegalInfrastructureStructMapping instance = Mappers.getMapper( DataCompanySeriousIllegalInfrastructureStructMapping.class );

	protected DataCompanySeriousIllegalId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanySeriousIllegalId.of(id);
	}
	protected Long map(DataCompanySeriousIllegalId dataCompanySeriousIllegalId){
		if (dataCompanySeriousIllegalId == null) {
			return null;
		}
		return dataCompanySeriousIllegalId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanySeriousIllegalInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanySeriousIllegalDO
	 * @return
	 */
	public abstract DataCompanySeriousIllegal dataCompanySeriousIllegalDOToDataCompanySeriousIllegal(@MappingTarget DataCompanySeriousIllegal dataCompanySeriousIllegal,DataCompanySeriousIllegalDO dataCompanySeriousIllegalDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanySeriousIllegalInfrastructureStructMapping#map(DataCompanySeriousIllegalId)}
	 * @param dataCompanySeriousIllegal
	 * @return
	 */
	public abstract DataCompanySeriousIllegalDO dataCompanySeriousIllegalToDataCompanySeriousIllegalDO(DataCompanySeriousIllegal dataCompanySeriousIllegal);

}
