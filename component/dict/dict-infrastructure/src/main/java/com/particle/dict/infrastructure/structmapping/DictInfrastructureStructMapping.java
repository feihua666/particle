package com.particle.dict.infrastructure.structmapping;

import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 字典 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper
public abstract class DictInfrastructureStructMapping {
	public static DictInfrastructureStructMapping instance = Mappers.getMapper( DictInfrastructureStructMapping.class );

	protected DictId map(Long id){
		if (id == null) {
			return null;
		}
		return DictId.of(id);
	}
	protected Long map(DictId dictId){
		if (dictId == null) {
			return null;
		}
		return dictId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DictInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dictDO
	 * @return
	 */
	public abstract Dict dictDOToDict(@MappingTarget Dict dict,DictDO dictDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DictInfrastructureStructMapping#map(DictId)}
	 * @param dict
	 * @return
	 */
	public abstract DictDO dictToDictDO(Dict dict);

}
