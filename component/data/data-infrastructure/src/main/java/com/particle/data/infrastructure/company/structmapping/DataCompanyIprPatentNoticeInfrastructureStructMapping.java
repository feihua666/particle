package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.data.domain.company.DataCompanyIprPatentNotice;
import com.particle.data.domain.company.DataCompanyIprPatentNoticeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利通知书信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentNoticeInfrastructureStructMapping {
	public static DataCompanyIprPatentNoticeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentNoticeInfrastructureStructMapping.class );

	protected DataCompanyIprPatentNoticeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentNoticeId.of(id);
	}
	protected Long map(DataCompanyIprPatentNoticeId dataCompanyIprPatentNoticeId){
		if (dataCompanyIprPatentNoticeId == null) {
			return null;
		}
		return dataCompanyIprPatentNoticeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentNoticeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentNoticeDO
	 * @return
	 */
	public abstract DataCompanyIprPatentNotice dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNotice(@MappingTarget DataCompanyIprPatentNotice dataCompanyIprPatentNotice,DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentNoticeInfrastructureStructMapping#map(DataCompanyIprPatentNoticeId)}
	 * @param dataCompanyIprPatentNotice
	 * @return
	 */
	public abstract DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeToDataCompanyIprPatentNoticeDO(DataCompanyIprPatentNotice dataCompanyIprPatentNotice);

}
