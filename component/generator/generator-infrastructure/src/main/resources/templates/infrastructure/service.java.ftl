package ${injection.pkg};

import ${injection.entity.pkg}.${injection.entity.className};
import ${superServiceClassPackage};

/**
 * <p>
 * ${injection.tableComment} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
