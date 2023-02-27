package com.particle.lowcode.adapter.generator.web.admin;

import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import com.particle.lowcode.client.generator.api.representation.ILowcodeSegmentGenRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenRenderGenCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateRenderCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenRenderGenVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenUpdateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateRenderVO;
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
 * 低代码生成后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Api(tags = "低代码生成pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/lowcode-segment-gen")
public class LowcodeSegmentGenAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;
	@Autowired
	private ILowcodeSegmentGenRepresentationApplicationService iLowcodeSegmentGenRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:create')")
	@ApiOperation("添加低代码生成")
	@PostMapping("/create")
	public SingleResponse<LowcodeSegmentGenVO> create(@RequestBody LowcodeSegmentGenCreateCommand lowcodeSegmentGenCreateCommand){
		return iLowcodeSegmentGenApplicationService.create(lowcodeSegmentGenCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:delete')")
	@ApiOperation("删除低代码生成")
	@DeleteMapping("/delete")
	public SingleResponse<LowcodeSegmentGenVO> delete(@RequestBody IdCommand deleteCommand){
		return iLowcodeSegmentGenApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:update')")
	@ApiOperation("更新低代码生成")
	@PutMapping("/update")
	public SingleResponse<LowcodeSegmentGenVO> update(@RequestBody LowcodeSegmentGenUpdateCommand lowcodeSegmentGenUpdateCommand){
		return iLowcodeSegmentGenApplicationService.update(lowcodeSegmentGenUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:update')")
	@ApiOperation("低代码生成更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<LowcodeSegmentGenVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iLowcodeSegmentGenRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:detail')")
	@ApiOperation("低代码生成详情展示")
	@GetMapping("/detail")
	public SingleResponse<LowcodeSegmentGenVO> queryDetail(IdCommand detailCommand){
		return iLowcodeSegmentGenRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:queryList')")
	@ApiOperation("列表查询低代码生成")
	@GetMapping("/list")
	public MultiResponse<LowcodeSegmentGenVO> queryList(LowcodeSegmentGenQueryListCommand lowcodeSegmentGenQueryListCommand){
		return iLowcodeSegmentGenRepresentationApplicationService.queryList(lowcodeSegmentGenQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:pageQuery')")
	@ApiOperation("分页查询低代码生成")
	@GetMapping("/page")
	public PageResponse<LowcodeSegmentGenVO> pageQueryList(LowcodeSegmentGenPageQueryCommand lowcodeSegmentGenPageQueryCommand){
		return iLowcodeSegmentGenRepresentationApplicationService.pageQuery(lowcodeSegmentGenPageQueryCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:reloadLowcodeModelJson')")
	@ApiOperation("重新加载模型json数据")
	@PutMapping("/reloadLowcodeModelJson")
	public SingleResponse<LowcodeSegmentGenVO> reloadLowcodeModelJson(@RequestBody IdCommand reloadCommand){
		return iLowcodeSegmentGenApplicationService.reloadLowcodeModelJson(reloadCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeSegmentGen:renderGen')")
	@ApiOperation("低代码生成设计和渲染")
	@PostMapping("/renderGen")
	public SingleResponse<LowcodeSegmentGenRenderGenVO> renderGen(@RequestBody LowcodeSegmentGenRenderGenCommand lowcodeSegmentGenRenderGenCommand){
		return iLowcodeSegmentGenApplicationService.renderGen(lowcodeSegmentGenRenderGenCommand);
	}
}