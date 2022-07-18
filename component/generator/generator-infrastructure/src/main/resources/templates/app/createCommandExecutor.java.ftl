package ${injection.pkg};

import ${injection.appStructMapping.pkg}.${injection.appStructMapping.className};
import ${injection.createCommand.pkg}.${injection.createCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
import ${injection.domainObject.pkg}.${injection.domainObject.className};
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
 * ${injection.tableComment} 创建指令执行器
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
	 * 执行${injection.tableComment}添加指令
	 * @param ${injection.createCommand.classNameVar}
	 * @return
	 */
	public SingleResponse<${injection.vo.className}> execute(@Valid ${injection.createCommand.className} ${injection.createCommand.classNameVar}) {
		${injection.domainObject.className} ${injection.domainObject.classNameVar} = createBy${injection.createCommand.className}(${injection.createCommand.classNameVar});
		boolean save = ${injection.gateway.classNameVar}.save(${injection.domainObject.classNameVar});
		if (save) {
			return SingleResponse.of(${injection.appStructMapping.className}.instance.to${injection.vo.className}(${injection.domainObject.classNameVar}));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据${injection.tableComment}创建指令创建${injection.tableComment}模型
	 * @param ${injection.createCommand.classNameVar}
	 * @return
	 */
	private ${injection.domainObject.className} createBy${injection.createCommand.className}(${injection.createCommand.className} ${injection.createCommand.classNameVar}){
		${injection.domainObject.className} ${injection.domainObject.classNameVar} = ${injection.domainObject.className}.create();
		${injection.createCommand.className}To${injection.domainObject.className}Mapping.instance.fill${injection.domainObject.className}By${injection.createCommand.className}(${injection.domainObject.classNameVar}, ${injection.createCommand.classNameVar});
		return ${injection.domainObject.classNameVar};
	}

	@Mapper
	interface  ${injection.createCommand.className}To${injection.domainObject.className}Mapping{
		${injection.createCommand.className}To${injection.domainObject.className}Mapping instance = Mappers.getMapper( ${injection.createCommand.className}To${injection.domainObject.className}Mapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param ${injection.domainObject.classNameVar}
		 * @param ${injection.createCommand.classNameVar}
		 */
		void fill${injection.domainObject.className}By${injection.createCommand.className}(@MappingTarget ${injection.domainObject.className} ${injection.domainObject.classNameVar}, ${injection.createCommand.className} ${injection.createCommand.classNameVar});
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
