package com.particle.oplog.infrastructure.error.structmapping;

import com.particle.oplog.domain.error.OpLogError;
import com.particle.oplog.domain.error.OpLogErrorId;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 操作异常日志 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpLogErrorInfrastructureStructMapping {
	public static OpLogErrorInfrastructureStructMapping instance = Mappers.getMapper( OpLogErrorInfrastructureStructMapping.class );

	protected OpLogErrorId map(Long id){
		if (id == null) {
			return null;
		}
		return OpLogErrorId.of(id);
	}
	protected Long map(OpLogErrorId opLogErrorId){
		if (opLogErrorId == null) {
			return null;
		}
		return opLogErrorId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogErrorInfrastructureStructMapping#map(java.lang.Long)}
	 * @param opLogErrorDO
	 * @return
	 */
	public abstract OpLogError opLogErrorDOToOpLogError(@MappingTarget OpLogError opLogError,OpLogErrorDO opLogErrorDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogErrorInfrastructureStructMapping#map(OpLogErrorId)}
	 * @param opLogError
	 * @return
	 */
	public abstract OpLogErrorDO opLogErrorToOpLogErrorDO(OpLogError opLogError);

}
