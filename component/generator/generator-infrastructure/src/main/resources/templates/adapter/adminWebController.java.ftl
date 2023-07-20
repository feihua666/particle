package ${injection.pkg};

import ${injection.applicationService.pkg}.${injection.applicationService.className};
import ${injection.representationApplicationService.pkg}.${injection.representationApplicationService.className};
import ${injection.createCommand.pkg}.${injection.createCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
import com.particle.common.client.dto.command.IdCommand;
import ${injection.updateCommand.pkg}.${injection.updateCommand.className};
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import ${injection.queryListCommand.pkg}.${injection.queryListCommand.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "${injection.tableComment}pc或平板端后台管理相关接口")
@RestController
@RequestMapping("${injection.urlPrefix}/${injection.entityUrlPath}")
public class ${injection.className} extends AbstractBaseWebAdapter {

	@Autowired
	private ${injection.applicationService.className} ${injection.applicationService.classNameVar};
	@Autowired
	private ${injection.representationApplicationService.className} ${injection.representationApplicationService.classNameVar};

	<#if injection.method.create>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:create')")
	@Operation(summary = "添加${injection.tableComment}")
	@PostMapping("/create")
	public SingleResponse<${injection.vo.className}> create(@RequestBody ${injection.createCommand.className} ${injection.createCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.create(${injection.createCommand.classNameVar});
	}
	</#if>

	<#if injection.method.delete>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:delete')")
	@Operation(summary = "删除${injection.tableComment}")
	@DeleteMapping("/delete")
	public SingleResponse<${injection.vo.className}> delete(@RequestBody IdCommand deleteCommand){
		return ${injection.applicationService.classNameVar}.delete(deleteCommand);
	}
	</#if>

	<#if injection.method.update>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:update')")
	@Operation(summary = "更新${injection.tableComment}")
	@PutMapping("/update")
	public SingleResponse<${injection.vo.className}> update(@RequestBody ${injection.updateCommand.className} ${injection.updateCommand.classNameVar}){
		return ${injection.applicationService.classNameVar}.update(${injection.updateCommand.classNameVar});
	}
	</#if>

	<#if injection.method.queryDetailForUpdate>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:update')")
	@Operation(summary = "${injection.tableComment}更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<${injection.vo.className}> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return ${injection.representationApplicationService.classNameVar}.queryDetailForUpdate(detailForUpdateCommand);
	}
	</#if>

	<#if injection.method.queryDetail>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:detail')")
	@Operation(summary = "${injection.tableComment}详情展示")
	@GetMapping("/detail")
	public SingleResponse<${injection.vo.className}> queryDetail(IdCommand detailCommand){
		return ${injection.representationApplicationService.classNameVar}.queryDetail(detailCommand);
	}
	</#if>

	<#if injection.method.queryList>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:queryList')")
	@Operation(summary = "列表查询${injection.tableComment}")
	@GetMapping("/list")
	public MultiResponse<${injection.vo.className}> queryList(${injection.queryListCommand.className} ${injection.queryListCommand.classNameVar}){
		return ${injection.representationApplicationService.classNameVar}.queryList(${injection.queryListCommand.classNameVar});
	}
	</#if>

	<#if injection.method.queryPage>
	@PreAuthorize("hasAuthority('${injection.authorityPrefix}:${injection.entityAuthority}:pageQuery')")
	@Operation(summary = "分页查询${injection.tableComment}")
	@GetMapping("/page")
	public PageResponse<${injection.vo.className}> pageQueryList(${injection.pageQueryCommand.className} ${injection.pageQueryCommand.classNameVar}){
		return ${injection.representationApplicationService.classNameVar}.pageQuery(${injection.pageQueryCommand.classNameVar});
	}
	</#if>

}