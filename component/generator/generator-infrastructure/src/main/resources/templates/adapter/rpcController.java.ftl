package ${injection.pkg};

import ${injection.applicationService.pkg}.${injection.applicationService.className};
import ${injection.rpcFeignClient.pkg}.${injection.rpcFeignClient.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ${injection.tableComment}远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Tag(name = "${injection.tableComment}远程调用相关接口")
@RestController
@RequestMapping("${injection.urlPrefix}/${injection.entityUrlPath}")
public class ${injection.className} extends AbstractBaseRpcAdapter implements ${injection.rpcFeignClient.className} {

	@Autowired
	private ${injection.applicationService.className} ${injection.applicationService.classNameVar};


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