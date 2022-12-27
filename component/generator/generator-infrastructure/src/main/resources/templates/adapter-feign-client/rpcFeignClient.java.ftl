package ${injection.pkg};

import org.springframework.cloud.openfeign.FeignClient;
<#list injection.imports as im>
import ${im};
</#list>
/**
 * <p>
 * ${injection.tableComment}远程调用
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@FeignClient(name = "${r'${particle.feign-client.name.'}${injection.feignClientConfigName}:${injection.feignClientDefaultName}${r'}'}",path = "${injection.urlPrefix}/${injection.entityUrlPath}")
public interface ${injection.className} {


	<#if injection.method.create>
	</#if>

	<#if injection.method.delete>
	</#if>

	<#if injection.method.update>
	</#if>

	<#if injection.method.queryDetailForUpdate>
	</#if>

	<#if injection.method.queryDetail>
	</#if>

	<#if injection.method.queryList>
	</#if>

	<#if injection.method.queryPage>
	</#if>

}
