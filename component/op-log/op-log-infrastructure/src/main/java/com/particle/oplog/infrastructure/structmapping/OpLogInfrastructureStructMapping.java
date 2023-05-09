package com.particle.oplog.infrastructure.structmapping;

import com.particle.oplog.infrastructure.dos.OpLogDO;
import com.particle.oplog.domain.OpLog;
import com.particle.oplog.domain.OpLogId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 操作日志 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Mapper
public abstract class OpLogInfrastructureStructMapping {
	public static OpLogInfrastructureStructMapping instance = Mappers.getMapper( OpLogInfrastructureStructMapping.class );

	protected OpLogId map(Long id){
		if (id == null) {
			return null;
		}
		return OpLogId.of(id);
	}
	protected Long map(OpLogId opLogId){
		if (opLogId == null) {
			return null;
		}
		return opLogId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogInfrastructureStructMapping#map(java.lang.Long)}
	 * @param opLogDO
	 * @return
	 */
	public abstract OpLog opLogDOToOpLog(@MappingTarget OpLog opLog,OpLogDO opLogDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogInfrastructureStructMapping#map(OpLogId)}
	 * @param opLog
	 * @return
	 */
	public abstract OpLogDO opLogToOpLogDO(OpLog opLog);

}
