package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.data.domain.company.DataCompanyIprPatentTransfer;
import com.particle.data.domain.company.DataCompanyIprPatentTransferId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利转让信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentTransferInfrastructureStructMapping {
	public static DataCompanyIprPatentTransferInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentTransferInfrastructureStructMapping.class );

	protected DataCompanyIprPatentTransferId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentTransferId.of(id);
	}
	protected Long map(DataCompanyIprPatentTransferId dataCompanyIprPatentTransferId){
		if (dataCompanyIprPatentTransferId == null) {
			return null;
		}
		return dataCompanyIprPatentTransferId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentTransferInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentTransferDO
	 * @return
	 */
	public abstract DataCompanyIprPatentTransfer dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransfer(@MappingTarget DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer,DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentTransferInfrastructureStructMapping#map(DataCompanyIprPatentTransferId)}
	 * @param dataCompanyIprPatentTransfer
	 * @return
	 */
	public abstract DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferToDataCompanyIprPatentTransferDO(DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer);

}
