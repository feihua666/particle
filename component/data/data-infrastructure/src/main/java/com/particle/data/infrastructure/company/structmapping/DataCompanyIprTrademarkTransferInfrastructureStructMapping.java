package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransfer;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标转让信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkTransferInfrastructureStructMapping {
	public static DataCompanyIprTrademarkTransferInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkTransferInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkTransferId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkTransferId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkTransferId dataCompanyIprTrademarkTransferId){
		if (dataCompanyIprTrademarkTransferId == null) {
			return null;
		}
		return dataCompanyIprTrademarkTransferId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkTransferInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkTransferDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransfer(@MappingTarget DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer,DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkTransferInfrastructureStructMapping#map(DataCompanyIprTrademarkTransferId)}
	 * @param dataCompanyIprTrademarkTransfer
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferToDataCompanyIprTrademarkTransferDO(DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer);

}
