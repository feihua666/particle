package ${injection.pkg};

import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * ${injection.tableComment} 防腐层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${injection.className} extends IBaseGateway<${injection.idObject.className},${injection.domainObject.className}> {
}
