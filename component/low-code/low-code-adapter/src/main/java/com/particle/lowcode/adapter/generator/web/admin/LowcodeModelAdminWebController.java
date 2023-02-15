package com.particle.lowcode.adapter.generator.web.admin;

import com.particle.global.dto.response.Response;
import com.particle.lowcode.client.generator.api.ILowcodeModelApplicationService;
import com.particle.lowcode.client.generator.api.representation.ILowcodeModelRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateByModelIdCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelUpdateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelQueryListCommand;
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
 * 低代码模型后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Api(tags = "低代码模型pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/lowcode-model")
public class LowcodeModelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeModelApplicationService iLowcodeModelApplicationService;
	@Autowired
	private ILowcodeModelRepresentationApplicationService iLowcodeModelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:create')")
	@ApiOperation("添加低代码模型")
	@PostMapping("/create")
	public SingleResponse<LowcodeModelVO> create(@RequestBody LowcodeModelCreateCommand lowcodeModelCreateCommand){
		return iLowcodeModelApplicationService.create(lowcodeModelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:delete')")
	@ApiOperation("删除低代码模型")
	@DeleteMapping("/delete")
	public SingleResponse<LowcodeModelVO> delete(@RequestBody IdCommand deleteCommand){
		return iLowcodeModelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:update')")
	@ApiOperation("更新低代码模型")
	@PutMapping("/update")
	public SingleResponse<LowcodeModelVO> update(@RequestBody LowcodeModelUpdateCommand lowcodeModelUpdateCommand){
		return iLowcodeModelApplicationService.update(lowcodeModelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:update')")
	@ApiOperation("低代码模型更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<LowcodeModelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iLowcodeModelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:detail')")
	@ApiOperation("低代码模型详情展示")
	@GetMapping("/detail")
	public SingleResponse<LowcodeModelVO> queryDetail(IdCommand detailCommand){
		return iLowcodeModelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:queryList')")
	@ApiOperation("列表查询低代码模型")
	@GetMapping("/list")
	public MultiResponse<LowcodeModelVO> queryList(LowcodeModelQueryListCommand lowcodeModelQueryListCommand){
		return iLowcodeModelRepresentationApplicationService.queryList(lowcodeModelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:pageQuery')")
	@ApiOperation("分页查询低代码模型")
	@GetMapping("/page")
	public PageResponse<LowcodeModelVO> pageQueryList(LowcodeModelPageQueryCommand lowcodeModelPageQueryCommand){
		return iLowcodeModelRepresentationApplicationService.pageQuery(lowcodeModelPageQueryCommand);
	}



	@PreAuthorize("hasAuthority('admin:web:lowcodeModel:loadByModelAndDatasource')")
	@ApiOperation("加载载模型项")
	@PostMapping("/loadByModelAndDatasource")
	public Response loadByModelAndDatasource(@RequestBody LowcodeModelItemCreateByModelIdCommand idCommand){
		return iLowcodeModelApplicationService.loadByModelAndDatasource(idCommand);
	}
}