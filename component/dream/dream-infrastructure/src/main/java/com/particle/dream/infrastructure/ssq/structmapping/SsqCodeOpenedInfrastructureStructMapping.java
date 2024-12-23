package com.particle.dream.infrastructure.ssq.structmapping;

import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.SsqCodeOpenedId;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 双色球开奖 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SsqCodeOpenedInfrastructureStructMapping {
	public static SsqCodeOpenedInfrastructureStructMapping instance = Mappers.getMapper( SsqCodeOpenedInfrastructureStructMapping.class );

	protected SsqCodeOpenedId map(Long id){
		if (id == null) {
			return null;
		}
		return SsqCodeOpenedId.of(id);
	}
	protected Long map(SsqCodeOpenedId ssqCodeOpenedId){
		if (ssqCodeOpenedId == null) {
			return null;
		}
		return ssqCodeOpenedId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SsqCodeOpenedInfrastructureStructMapping#map(java.lang.Long)}
	 * @param ssqCodeOpenedDO
	 * @return
	 */
	public abstract SsqCodeOpened ssqCodeOpenedDOToSsqCodeOpened(@MappingTarget SsqCodeOpened ssqCodeOpened,SsqCodeOpenedDO ssqCodeOpenedDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SsqCodeOpenedInfrastructureStructMapping#map(SsqCodeOpenedId)}
	 * @param ssqCodeOpened
	 * @return
	 */
	public abstract SsqCodeOpenedDO ssqCodeOpenedToSsqCodeOpenedDO(SsqCodeOpened ssqCodeOpened);

}
