package com.particle.dream.infrastructure.ssq.structmapping;

import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.dream.domain.ssq.SsqCode;
import com.particle.dream.domain.ssq.SsqCodeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 双色球号码 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Mapper
public abstract class SsqCodeInfrastructureStructMapping {
	public static SsqCodeInfrastructureStructMapping instance = Mappers.getMapper( SsqCodeInfrastructureStructMapping.class );

	protected SsqCodeId map(Long id){
		if (id == null) {
			return null;
		}
		return SsqCodeId.of(id);
	}
	protected Long map(SsqCodeId ssqCodeId){
		if (ssqCodeId == null) {
			return null;
		}
		return ssqCodeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SsqCodeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param ssqCodeDO
	 * @return
	 */
	public abstract SsqCode ssqCodeDOToSsqCode(@MappingTarget SsqCode ssqCode,SsqCodeDO ssqCodeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SsqCodeInfrastructureStructMapping#map(SsqCodeId)}
	 * @param ssqCode
	 * @return
	 */
	public abstract SsqCodeDO ssqCodeToSsqCodeDO(SsqCode ssqCode);

}
