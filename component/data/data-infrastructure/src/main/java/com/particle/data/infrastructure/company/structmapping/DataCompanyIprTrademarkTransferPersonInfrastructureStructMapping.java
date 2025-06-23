package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPersonId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标转让人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping {
	public static DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkTransferPersonId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkTransferPersonId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkTransferPersonId dataCompanyIprTrademarkTransferPersonId){
		if (dataCompanyIprTrademarkTransferPersonId == null) {
			return null;
		}
		return dataCompanyIprTrademarkTransferPersonId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkTransferPersonDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPerson(@MappingTarget DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson,DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping#map(DataCompanyIprTrademarkTransferPersonId)}
	 * @param dataCompanyIprTrademarkTransferPerson
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonToDataCompanyIprTrademarkTransferPersonDO(DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson);

}
