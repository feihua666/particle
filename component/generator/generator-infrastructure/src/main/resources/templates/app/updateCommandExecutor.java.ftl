package ${injection.pkg};

import ${injection.appStructMapping.pkg}.${injection.appStructMapping.className};
import ${injection.updateCommand.pkg}.${injection.updateCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
import ${injection.gateway.pkg}.${injection.gateway.className};
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * ${injection.tableComment} 更新指令执行器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Component
@Validated
public class ${injection.className}  extends AbstractBaseExecutor {

	private ${injection.gateway.className} ${injection.gateway.classNameVar};

	/**
	 * 执行 ${injection.tableComment} 更新指令
	 * @param ${injection.updateCommand.classNameVar}
	 * @return
	 */
	public SingleResponse<${injection.vo.className}> execute(@Valid ${injection.updateCommand.className} ${injection.updateCommand.classNameVar}) {
		${injection.domainObject.className} ${injection.domainObject.classNameVar} = createBy${injection.updateCommand.className}(${injection.updateCommand.classNameVar});
		boolean save = ${injection.gateway.classNameVar}.save(${injection.domainObject.classNameVar});
		if (save) {
			return SingleResponse.of(${injection.appStructMapping.className}.instance.to${injection.vo.className}(${injection.domainObject.classNameVar}));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param ${injection.updateCommand.classNameVar}
	 * @return
	 */
	private ${injection.domainObject.className} createBy${injection.updateCommand.className}(${injection.updateCommand.className} ${injection.updateCommand.classNameVar}){
		${injection.domainObject.className} ${injection.domainObject.classNameVar} = ${injection.domainObject.className}.create();
		${injection.updateCommand.className}To${injection.domainObject.className}Mapping.instance.fill${injection.domainObject.className}By${injection.updateCommand.className}(${injection.domainObject.classNameVar}, ${injection.updateCommand.classNameVar});
		return ${injection.domainObject.classNameVar};
	}

	@Mapper
	interface ${injection.updateCommand.className}To${injection.domainObject.className}Mapping{
		${injection.updateCommand.className}To${injection.domainObject.className}Mapping instance = Mappers.getMapper(${injection.updateCommand.className}To${injection.domainObject.className}Mapping.class );

		default ${injection.idObject.className} map(Long id){
			if (id == null) {
				return null;
			}
			return ${injection.idObject.className}.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param ${injection.domainObject.classNameVar}
		 * @param ${injection.updateCommand.classNameVar}
		 */
		void fill${injection.domainObject.className}By${injection.updateCommand.className}(@MappingTarget ${injection.domainObject.className} ${injection.domainObject.classNameVar}, ${injection.updateCommand.className} ${injection.updateCommand.classNameVar});
	}

	/**
	 * 注入使用set方法
	 * @param ${injection.gateway.classNameVar}
	 */
	@Autowired
	public void set${injection.gateway.className}(${injection.gateway.className} ${injection.gateway.classNameVar}) {
		this.${injection.gateway.classNameVar} = ${injection.gateway.classNameVar};
	}
}
