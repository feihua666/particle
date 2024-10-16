package com.particle.openplatform.infrastructure.openapi.structmapping;

import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiLimitRuleDO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRule;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRuleId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口限制规则 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Mapper
public abstract class OpenplatformOpenapiLimitRuleInfrastructureStructMapping {
	public static OpenplatformOpenapiLimitRuleInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiLimitRuleInfrastructureStructMapping.class );

	protected OpenplatformOpenapiLimitRuleId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiLimitRuleId.of(id);
	}
	protected Long map(OpenplatformOpenapiLimitRuleId openplatformOpenapiLimitRuleId){
		if (openplatformOpenapiLimitRuleId == null) {
			return null;
		}
		return openplatformOpenapiLimitRuleId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiLimitRuleInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiLimitRuleDO
	 * @return
	 */
	public abstract OpenplatformOpenapiLimitRule openplatformOpenapiLimitRuleDOToOpenplatformOpenapiLimitRule(@MappingTarget OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule,OpenplatformOpenapiLimitRuleDO openplatformOpenapiLimitRuleDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiLimitRuleInfrastructureStructMapping#map(OpenplatformOpenapiLimitRuleId)}
	 * @param openplatformOpenapiLimitRule
	 * @return
	 */
	public abstract OpenplatformOpenapiLimitRuleDO openplatformOpenapiLimitRuleToOpenplatformOpenapiLimitRuleDO(OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule);

}
