package com.particle.oplog.infrastructure.error.structmapping;

import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import com.particle.oplog.domain.error.OpLogErrorContent;
import com.particle.oplog.domain.error.OpLogErrorContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 操作异常日志内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Mapper
public abstract class OpLogErrorContentInfrastructureStructMapping {
	public static OpLogErrorContentInfrastructureStructMapping instance = Mappers.getMapper( OpLogErrorContentInfrastructureStructMapping.class );

	protected OpLogErrorContentId map(Long id){
		if (id == null) {
			return null;
		}
		return OpLogErrorContentId.of(id);
	}
	protected Long map(OpLogErrorContentId opLogErrorContentId){
		if (opLogErrorContentId == null) {
			return null;
		}
		return opLogErrorContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogErrorContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param opLogErrorContentDO
	 * @return
	 */
	public abstract OpLogErrorContent opLogErrorContentDOToOpLogErrorContent(@MappingTarget OpLogErrorContent opLogErrorContent,OpLogErrorContentDO opLogErrorContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogErrorContentInfrastructureStructMapping#map(OpLogErrorContentId)}
	 * @param opLogErrorContent
	 * @return
	 */
	public abstract OpLogErrorContentDO opLogErrorContentToOpLogErrorContentDO(OpLogErrorContent opLogErrorContent);

}
