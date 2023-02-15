package com.particle.lowcode.adapter.generator.web.admin;

import com.particle.lowcode.client.generator.api.ILowcodeSegmentTemplateApplicationService;
import com.particle.lowcode.client.generator.api.representation.ILowcodeSegmentTemplateRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateRenderCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateRenderVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateUpdateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplatePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplateQueryListCommand;
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
 * 低代码片段模板后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Api(tags = "低代码片段模板pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/lowcode-segment-template")
public class LowcodeSegmentTemplateAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeSegmentTemplateApplicationService iLowcodeSegmentTemplateApplicationService;
	@Autowired
	private ILowcodeSegmentTemplateRepresentationApplicationService iLowcodeSegmentTemplateRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:create')")
	@ApiOperation("添加低代码片段模板")
	@PostMapping("/create")
	public SingleResponse<LowcodeSegmentTemplateVO> create(@RequestBody LowcodeSegmentTemplateCreateCommand lowcodeSegmentTemplateCreateCommand){
		return iLowcodeSegmentTemplateApplicationService.create(lowcodeSegmentTemplateCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:delete')")
	@ApiOperation("删除低代码片段模板")
	@DeleteMapping("/delete")
	public SingleResponse<LowcodeSegmentTemplateVO> delete(@RequestBody IdCommand deleteCommand){
		return iLowcodeSegmentTemplateApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:update')")
	@ApiOperation("更新低代码片段模板")
	@PutMapping("/update")
	public SingleResponse<LowcodeSegmentTemplateVO> update(@RequestBody LowcodeSegmentTemplateUpdateCommand lowcodeSegmentTemplateUpdateCommand){
		return iLowcodeSegmentTemplateApplicationService.update(lowcodeSegmentTemplateUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:update')")
	@ApiOperation("低代码片段模板更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<LowcodeSegmentTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iLowcodeSegmentTemplateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:detail')")
	@ApiOperation("低代码片段模板详情展示")
	@GetMapping("/detail")
	public SingleResponse<LowcodeSegmentTemplateVO> queryDetail(IdCommand detailCommand){
		return iLowcodeSegmentTemplateRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:queryList')")
	@ApiOperation("列表查询低代码片段模板")
	@GetMapping("/list")
	public MultiResponse<LowcodeSegmentTemplateVO> queryList(LowcodeSegmentTemplateQueryListCommand lowcodeSegmentTemplateQueryListCommand){
		return iLowcodeSegmentTemplateRepresentationApplicationService.queryList(lowcodeSegmentTemplateQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:pageQuery')")
	@ApiOperation("分页查询低代码片段模板")
	@GetMapping("/page")
	public PageResponse<LowcodeSegmentTemplateVO> pageQueryList(LowcodeSegmentTemplatePageQueryCommand lowcodeSegmentTemplatePageQueryCommand){
		return iLowcodeSegmentTemplateRepresentationApplicationService.pageQuery(lowcodeSegmentTemplatePageQueryCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentTemplate:renderTest')")
	@ApiOperation("片段模板渲染测试")
	@PostMapping("/renderTest")
	public SingleResponse<LowcodeSegmentTemplateRenderVO> renderTest(@RequestBody LowcodeSegmentTemplateRenderCommand lowcodeSegmentTemplateRenderCommand){
		return iLowcodeSegmentTemplateApplicationService.renderTest(lowcodeSegmentTemplateRenderCommand);
	}

}