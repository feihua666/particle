package ${injection.pkg};

import ${injection.entity.pkg}.${injection.entity.className};
import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * ${injection.tableComment} 基础设施层数据实体映射转换
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public abstract class ${injection.className} {
	public static ${injection.className} instance = Mappers.getMapper( ${injection.className}.class );

	protected ${injection.idObject.className} map(Long id){
		if (id == null) {
			return null;
		}
		return ${injection.idObject.className}.of(id);
	}
	protected Long map(${injection.idObject.className} ${injection.idObject.classNameVar}){
		if (${injection.idObject.classNameVar} == null) {
			return null;
		}
		return ${injection.idObject.classNameVar}.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ${injection.className}#map(java.lang.Long)}
	 * @param ${injection.entity.classNameVar}
	 * @return
	 */
	public abstract ${injection.domainObject.className} ${injection.entity.classNameVar}To${injection.domainObject.className}(@MappingTarget ${injection.domainObject.className} ${injection.domainObject.classNameVar},${injection.entity.className} ${injection.entity.classNameVar});

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ${injection.className}#map(${injection.idObject.className})}
	 * @param ${injection.domainObject.classNameVar}
	 * @return
	 */
	public abstract ${injection.entity.className} ${injection.domainObject.classNameVar}To${injection.entity.className}(${injection.domainObject.className} ${injection.domainObject.classNameVar});

}
