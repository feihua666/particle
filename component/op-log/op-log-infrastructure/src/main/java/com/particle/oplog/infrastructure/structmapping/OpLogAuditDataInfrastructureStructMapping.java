package com.particle.oplog.infrastructure.structmapping;

import com.particle.oplog.domain.OpLogAuditData;
import com.particle.oplog.domain.OpLogAuditDataId;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 操作日志审计数据 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpLogAuditDataInfrastructureStructMapping {
	public static OpLogAuditDataInfrastructureStructMapping instance = Mappers.getMapper( OpLogAuditDataInfrastructureStructMapping.class );

	protected OpLogAuditDataId map(Long id){
		if (id == null) {
			return null;
		}
		return OpLogAuditDataId.of(id);
	}
	protected Long map(OpLogAuditDataId opLogAuditDataId){
		if (opLogAuditDataId == null) {
			return null;
		}
		return opLogAuditDataId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogAuditDataInfrastructureStructMapping#map(java.lang.Long)}
	 * @param opLogAuditDataDO
	 * @return
	 */
	public abstract OpLogAuditData opLogAuditDataDOToOpLogAuditData(@MappingTarget OpLogAuditData opLogAuditData,OpLogAuditDataDO opLogAuditDataDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogAuditDataInfrastructureStructMapping#map(OpLogAuditDataId)}
	 * @param opLogAuditData
	 * @return
	 */
	public abstract OpLogAuditDataDO opLogAuditDataToOpLogAuditDataDO(OpLogAuditData opLogAuditData);

}
