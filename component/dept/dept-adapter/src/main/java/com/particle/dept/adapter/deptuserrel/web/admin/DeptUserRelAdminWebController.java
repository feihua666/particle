package com.particle.dept.adapter.deptuserrel.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import com.particle.dept.client.deptuserrel.api.representation.IDeptUserRelRepresentationApplicationService;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelPageQueryCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 部门用户关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
@Tag(name = "部门用户关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dept_user_rel")
public class DeptUserRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;
	@Autowired
	private IDeptUserRelRepresentationApplicationService iDeptUserRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:create')")
	@Operation(summary = "添加部门用户关系")
	@PostMapping("/create")
	@OpLog(name = "添加部门用户关系",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.create)
	public SingleResponse<DeptUserRelVO> create(@RequestBody DeptUserRelCreateCommand deptUserRelCreateCommand){
		return iDeptUserRelApplicationService.create(deptUserRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:delete')")
	@Operation(summary = "删除部门用户关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除部门用户关系",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.delete)
	public SingleResponse<DeptUserRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iDeptUserRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:update')")
	@Operation(summary = "更新部门用户关系")
	@PutMapping("/update")
	@OpLog(name = "更新部门用户关系",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.update)
	public SingleResponse<DeptUserRelVO> update(@RequestBody DeptUserRelUpdateCommand deptUserRelUpdateCommand){
		return iDeptUserRelApplicationService.update(deptUserRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:update')")
	@Operation(summary = "部门用户关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DeptUserRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDeptUserRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:detail')")
	@Operation(summary = "部门用户关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<DeptUserRelVO> queryDetail(IdCommand detailCommand){
		return iDeptUserRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:queryList')")
	@Operation(summary = "列表查询部门用户关系")
	@GetMapping("/list")
	public MultiResponse<DeptUserRelVO> queryList(DeptUserRelQueryListCommand deptUserRelQueryListCommand){
		return iDeptUserRelRepresentationApplicationService.queryList(deptUserRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptUserRel:pageQuery')")
	@Operation(summary = "分页查询部门用户关系")
	@GetMapping("/page")
	public PageResponse<DeptUserRelVO> pageQueryList(DeptUserRelPageQueryCommand deptUserRelPageQueryCommand){
		return iDeptUserRelRepresentationApplicationService.pageQuery(deptUserRelPageQueryCommand);
	}

}
