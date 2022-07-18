package ${injection.pkg};

import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
import ${injection.gateway.pkg}.${injection.gateway.className};
import ${injection.service.pkg}.${injection.service.className};
import ${injection.entity.pkg}.${injection.entity.className};
import ${injection.infrastructureStructMapping.pkg}.${injection.infrastructureStructMapping.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * ${injection.tableComment} 防腐层网关实现
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Component
public class ${injection.className} extends AbstractBaseGatewayImpl implements ${injection.gateway.className} {

	private ${injection.service.className} ${injection.service.classNameVar};

	@Override
	public ${injection.domainObject.className} getById(${injection.idObject.className} ${injection.idObject.classNameVar}) {
		${injection.entity.className} byId = ${injection.service.classNameVar}.getById(${injection.idObject.classNameVar}.getId());
		${injection.domainObject.className} ${injection.domainObject.classNameVar} = DomainFactory.create(${injection.domainObject.className}.class);
		${injection.domainObject.classNameVar} = ${injection.infrastructureStructMapping.className}.instance. ${injection.entity.classNameVar}To${injection.domainObject.className}(${injection.domainObject.classNameVar},byId);
		return ${injection.domainObject.classNameVar};
	}

	@Override
	public boolean save(${injection.domainObject.className} ${injection.domainObject.classNameVar}) {
		${injection.entity.className} ${injection.entity.classNameVar} = ${injection.infrastructureStructMapping.className}.instance.${injection.domainObject.classNameVar}To${injection.entity.className}(${injection.domainObject.classNameVar});
		if (${injection.entity.classNameVar}.getId() == null) {
			${injection.entity.className} add = ${injection.service.classNameVar}.add(${injection.entity.classNameVar});
			${injection.domainObject.classNameVar}.setId(${injection.idObject.className}.of(add.getId()));
			return add != null;
		}
		${injection.entity.className} update = ${injection.service.classNameVar}.update(${injection.entity.classNameVar});
		return update != null;
	}

	@Override
	public boolean delete(${injection.idObject.className} ${injection.idObject.classNameVar}) {
		return ${injection.service.classNameVar}.deleteById(${injection.idObject.classNameVar}.getId());
	}


	@Autowired
	public void set${injection.service.className}(${injection.service.className} ${injection.service.classNameVar}) {
		this.${injection.service.classNameVar} = ${injection.service.classNameVar};
	}
}
