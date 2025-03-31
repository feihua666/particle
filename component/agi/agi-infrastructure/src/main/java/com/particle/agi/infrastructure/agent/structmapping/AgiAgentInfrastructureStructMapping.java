package com.particle.agi.infrastructure.agent.structmapping;

import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 智能体 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentInfrastructureStructMapping {
	public static AgiAgentInfrastructureStructMapping instance = Mappers.getMapper( AgiAgentInfrastructureStructMapping.class );

	protected AgiAgentId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAgentId.of(id);
	}
	protected Long map(AgiAgentId agiAgentId){
		if (agiAgentId == null) {
			return null;
		}
		return agiAgentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAgentDO
	 * @return
	 */
	public abstract AgiAgent agiAgentDOToAgiAgent(@MappingTarget AgiAgent agiAgent,AgiAgentDO agiAgentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentInfrastructureStructMapping#map(AgiAgentId)}
	 * @param agiAgent
	 * @return
	 */
	public abstract AgiAgentDO agiAgentToAgiAgentDO(AgiAgent agiAgent);

}
