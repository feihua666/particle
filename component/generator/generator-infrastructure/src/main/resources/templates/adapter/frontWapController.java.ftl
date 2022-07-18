package ${injection.pkg};

import ${injection.applicationService.pkg}.${injection.applicationService.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ${injection.tableComment}前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${injection.tableComment}wap端前台应用相关接口")
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