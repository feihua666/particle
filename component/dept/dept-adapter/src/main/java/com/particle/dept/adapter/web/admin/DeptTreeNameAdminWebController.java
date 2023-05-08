package com.particle.dept.adapter.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dept.client.api.IDeptTreeNameApplicationService;
import com.particle.dept.client.api.representation.IDeptTreeNameRepresentationApplicationService;
import com.particle.dept.client.dto.command.DeptTreeNameCreateCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.DeptTreeNameUpdateCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeNamePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeNameQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dataaudit.op.OpLog;
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
 * 部门树名称后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Api(tags = "部门树名称pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dept_tree_name")
public class DeptTreeNameAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptTreeNameApplicationService iDeptTreeNameApplicationService;
	@Autowired
	private IDeptTreeNameRepresentationApplicationService iDeptTreeNameRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:create')")
	@ApiOperation("添加部门树名称")
	@PostMapping("/create")
	@OpLog(name = "添加部门树名称",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.create)
	public SingleResponse<DeptTreeNameVO> create(@RequestBody DeptTreeNameCreateCommand deptTreeNameCreateCommand){
		return iDeptTreeNameApplicationService.create(deptTreeNameCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:delete')")
	@ApiOperation("删除部门树名称")
	@DeleteMapping("/delete")
	@OpLog(name = "删除部门树名称",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.delete)
	public SingleResponse<DeptTreeNameVO> delete(@RequestBody IdCommand deleteCommand){
		return iDeptTreeNameApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:update')")
	@ApiOperation("更新部门树名称")
	@PutMapping("/update")
	@OpLog(name = "更新部门树名称",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.update)
	public SingleResponse<DeptTreeNameVO> update(@RequestBody DeptTreeNameUpdateCommand deptTreeNameUpdateCommand){
		return iDeptTreeNameApplicationService.update(deptTreeNameUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:update')")
	@ApiOperation("部门树名称更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DeptTreeNameVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDeptTreeNameRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:detail')")
	@ApiOperation("部门树名称详情展示")
	@GetMapping("/detail")
	public SingleResponse<DeptTreeNameVO> queryDetail(IdCommand detailCommand){
		return iDeptTreeNameRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:queryList')")
	@ApiOperation("列表查询部门树名称")
	@GetMapping("/list")
	public MultiResponse<DeptTreeNameVO> queryList(DeptTreeNameQueryListCommand deptTreeNameQueryListCommand){
		return iDeptTreeNameRepresentationApplicationService.queryList(deptTreeNameQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeName:pageQuery')")
	@ApiOperation("分页查询部门树名称")
	@GetMapping("/page")
	public PageResponse<DeptTreeNameVO> pageQueryList(DeptTreeNamePageQueryCommand deptTreeNamePageQueryCommand){
		return iDeptTreeNameRepresentationApplicationService.pageQuery(deptTreeNamePageQueryCommand);
	}

}