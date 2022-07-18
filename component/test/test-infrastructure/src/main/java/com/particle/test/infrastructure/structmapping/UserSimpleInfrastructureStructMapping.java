package com.particle.test.infrastructure.structmapping;

import com.particle.test.infrastructure.dos.UserSimpleDO;
import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 简单用户 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Mapper
public abstract class UserSimpleInfrastructureStructMapping {
	public static UserSimpleInfrastructureStructMapping instance = Mappers.getMapper( UserSimpleInfrastructureStructMapping.class );

	protected UserSimpleId map(Long id){
		if (id == null) {
			return null;
		}
		return UserSimpleId.of(id);
	}
	protected Long map(UserSimpleId userSimpleId){
		if (userSimpleId == null) {
			return null;
		}
		return userSimpleId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserSimpleInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userSimpleDO
	 * @return
	 */
	public abstract UserSimple userSimpleDOToUserSimple(@MappingTarget UserSimple userSimple,UserSimpleDO userSimpleDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserSimpleInfrastructureStructMapping#map(UserSimpleId)}
	 * @param userSimple
	 * @return
	 */
	public abstract UserSimpleDO userSimpleToUserSimpleDO(UserSimple userSimple);

}
