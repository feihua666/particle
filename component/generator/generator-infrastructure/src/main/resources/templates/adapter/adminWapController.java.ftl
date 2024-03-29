package ${injection.pkg};

import ${injection.applicationService.pkg}.${injection.applicationService.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ${injection.tableComment}后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Tag(name = "${injection.tableComment}wap端后台管理相关接口")
@RestController
@RequestMapping("${injection.urlPrefix}/${injection.entityUrlPath}")
public class ${injection.className} extends AbstractBaseWapAdapter {

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