package ${injection.pkg};

import ${injection.appStructMapping.pkg}.${injection.appStructMapping.className};
import com.particle.common.client.dto.command.IdCommand;
import ${injection.vo.pkg}.${injection.vo.className};
import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
import ${injection.gateway.pkg}.${injection.gateway.className};
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
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
	 * 执行 ${injection.tableComment} 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<${injection.vo.className}> execute(@Valid IdCommand deleteCommand) {
		${injection.idObject.className} ${injection.idObject.classNameVar} = ${injection.idObject.className}.of(deleteCommand.getId());
		${injection.domainObject.className} byId = ${injection.gateway.classNameVar}.getById(${injection.idObject.classNameVar});
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = ${injection.gateway.classNameVar}.delete(${injection.idObject.classNameVar});
		if (delete) {
			return SingleResponse.of(${injection.appStructMapping.className}.instance.to${injection.vo.className}(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
