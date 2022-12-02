package com.particle.func.adapter.web.admin;

import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.func.client.api.representation.IFuncGroupRepresentationApplicationService;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.client.dto.command.representation.FuncGroupQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryDetailCommand;
import com.particle.func.client.dto.command.FuncGroupDeleteCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryListCommand;
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
 * 功能组后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Api(tags = "功能组pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/func-group")
public class FuncGroupAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;
	@Autowired
	private IFuncGroupRepresentationApplicationService iFuncGroupRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:funcGroup:create')")
	@ApiOperation("添加功能组")
	@PostMapping("/create")
	public SingleResponse<FuncGroupVO> create(@RequestBody FuncGroupCreateCommand funcGroupCreateCommand){
		return iFuncGroupApplicationService.create(funcGroupCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:delete')")
	@ApiOperation("删除功能组")
	@DeleteMapping("/delete")
	public SingleResponse<FuncGroupVO> delete(@RequestBody FuncGroupDeleteCommand funcGroupDeleteCommand){
		return iFuncGroupApplicationService.delete(funcGroupDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:update')")
	@ApiOperation("更新功能组")
	@PutMapping("/update")
	public SingleResponse<FuncGroupVO> update(@RequestBody FuncGroupUpdateCommand funcGroupUpdateCommand){
		return iFuncGroupApplicationService.update(funcGroupUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:update')")
	@ApiOperation("功能组更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<FuncGroupVO> queryDetailForUpdate(FuncGroupQueryDetailForUpdateCommand funcGroupQueryDetailForUpdateCommand){
		return iFuncGroupRepresentationApplicationService.queryDetailForUpdate(funcGroupQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:detail')")
	@ApiOperation("功能组详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncGroupVO> queryDetail(FuncGroupQueryDetailCommand funcGroupQueryDetailCommand){
		return iFuncGroupRepresentationApplicationService.queryDetail(funcGroupQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:queryList')")
	@ApiOperation("列表查询功能组")
	@GetMapping("/list")
	public MultiResponse<FuncGroupVO> queryList(FuncGroupQueryListCommand funcGroupQueryListCommand){
		return iFuncGroupRepresentationApplicationService.queryList(funcGroupQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:pageQuery')")
	@ApiOperation("分页查询功能组")
	@GetMapping("/page")
	public PageResponse<FuncGroupVO> pageQueryList(FuncGroupPageQueryCommand funcGroupPageQueryCommand){
		return iFuncGroupRepresentationApplicationService.pageQuery(funcGroupPageQueryCommand);
	}

}