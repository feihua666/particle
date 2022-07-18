package ${injection.pkg};

import ${injection.applicationService.pkg}.${injection.applicationService.className};
import ${injection.createCommand.pkg}.${injection.createCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
import ${injection.queryDetailForUpdateCommand.pkg}.${injection.queryDetailForUpdateCommand.className};
import ${injection.queryDetailCommand.pkg}.${injection.queryDetailCommand.className};
import ${injection.deleteCommand.pkg}.${injection.deleteCommand.className};
import ${injection.updateCommand.pkg}.${injection.updateCommand.className};
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import ${injection.queryListCommand.pkg}.${injection.queryListCommand.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * ${injection.tableComment}后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${injection.tableComment}pc或平板端后台管理相关接口")
@RestController
@RequestMapping("${injection.urlPrefix}/${injection.entityUrlPath}")
public class ${injection.className} extends AbstractBaseWebAdapter {

	@Autowired
	private ${injection.applicationService.className} ${injection.applicationService.classNameVar};

	<#if injection.method.create>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:create')")
	@ApiOperation("添加${injection.tableComment}")
	@PostMapping("/create")
	public SingleResponse<${injection.vo.className}> create(@RequestBody ${injection.createCommand.className} ${injection.createCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.create(${injection.createCommand.classNameVar});
	}
	</#if>

	<#if injection.method.delete>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:delete')")
	@ApiOperation("删除${injection.tableComment}")
	@DeleteMapping("/delete")
	public SingleResponse<${injection.vo.className}> delete(@RequestBody ${injection.deleteCommand.className} ${injection.deleteCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.delete(${injection.deleteCommand.classNameVar});
	}
	</#if>

	<#if injection.method.update>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:update')")
	@ApiOperation("更新${injection.tableComment}")
	@PutMapping("/update")
	public SingleResponse<${injection.vo.className}> update(@RequestBody ${injection.updateCommand.className} ${injection.updateCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.update(${injection.updateCommand.classNameVar});
	}
	</#if>

	<#if injection.method.queryDetailForUpdate>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:update')")
	@ApiOperation("${injection.tableComment}更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<${injection.vo.className}> queryDetailForUpdate(@RequestBody ${injection.queryDetailForUpdateCommand.className} ${injection.queryDetailForUpdateCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.queryDetailForUpdate(${injection.queryDetailForUpdateCommand.classNameVar});
	}
	</#if>

	<#if injection.method.queryDetail>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:detail')")
	@ApiOperation("${injection.tableComment}详情展示")
	@GetMapping("/detail")
	public SingleResponse<${injection.vo.className}> queryDetail(@RequestBody ${injection.queryDetailCommand.className} ${injection.queryDetailCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.queryDetail(${injection.queryDetailCommand.classNameVar});
	}
	</#if>

	<#if injection.method.queryList>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:queryList')")
	@ApiOperation("列表查询${injection.tableComment}")
	@GetMapping("/list")
	public MultiResponse<${injection.vo.className}> queryList(@RequestBody ${injection.queryListCommand.className} ${injection.queryListCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.queryList(${injection.queryListCommand.classNameVar});
	}
	</#if>

	<#if injection.method.queryPage>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:pageQuery')")
	@ApiOperation("分页查询${injection.tableComment}")
	@GetMapping("/page")
	public PageResponse<${injection.vo.className}> pageQueryList(@RequestBody ${injection.pageQueryCommand.className} ${injection.pageQueryCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.pageQuery(${injection.pageQueryCommand.classNameVar});
	}
	</#if>

}