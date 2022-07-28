package ${injection.pkg};

import ${injection.entity.pkg}.${injection.entity.className};
import ${injection.mapper.pkg}.${injection.mapper.className};
import ${injection.service.pkg}.${injection.service.className};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * ${injection.tableComment} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Component
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
	private IBaseQueryCommandMapStruct<${entity}> queryCommandMapStruct;

	@Override
	protected ${entity} queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<${entity}> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
</#if>
