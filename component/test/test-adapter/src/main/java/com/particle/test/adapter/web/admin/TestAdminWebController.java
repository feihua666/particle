package com.particle.test.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.test.client.api.ITestApplicationService;
import com.particle.test.client.api.representation.ITestRepresentationApplicationService;
import com.particle.test.client.dto.command.TestCreateCommand;
import com.particle.test.client.dto.command.TestUpdateCommand;
import com.particle.test.client.dto.command.representation.TestPageQueryCommand;
import com.particle.test.client.dto.command.representation.TestQueryListCommand;
import com.particle.test.client.dto.data.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 测试后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "测试pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/test")
public class TestAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITestApplicationService iTestApplicationService;
	@Autowired
	private ITestRepresentationApplicationService iTestRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:test:create')")
	@ApiOperation("添加测试")
	@PostMapping("/create")
	public SingleResponse<TestVO> create(@RequestBody TestCreateCommand testCreateCommand){
		return iTestApplicationService.create(testCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:test:delete')")
	@ApiOperation("删除测试")
	@DeleteMapping("/delete")
	public SingleResponse<TestVO> delete(@RequestBody IdCommand testDeleteCommand){
		return iTestApplicationService.delete(testDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:test:update')")
	@ApiOperation("更新测试")
	@PutMapping("/update")
	public SingleResponse<TestVO> update(@RequestBody TestUpdateCommand testUpdateCommand){
		return iTestApplicationService.update(testUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:test:update')")
	@ApiOperation("测试更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TestVO> queryDetailForUpdate(IdCommand testQueryDetailForUpdateCommand){
		return iTestRepresentationApplicationService.queryDetailForUpdate(testQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:test:detail')")
	@ApiOperation("测试详情展示")
	@GetMapping("/detail")
	public SingleResponse<TestVO> queryDetail(IdCommand testQueryDetailCommand){
		return iTestRepresentationApplicationService.queryDetail(testQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:test:queryList')")
	@ApiOperation("列表查询测试")
	@GetMapping("/list")
	public MultiResponse<TestVO> queryList(TestQueryListCommand testQueryListCommand){
		return iTestRepresentationApplicationService.queryList(testQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:test:pageQuery')")
	@ApiOperation("分页查询测试")
	@GetMapping("/page")
	public PageResponse<TestVO> pageQueryList(TestPageQueryCommand testPageQueryCommand){
		return iTestRepresentationApplicationService.pageQuery(testPageQueryCommand);
	}

}