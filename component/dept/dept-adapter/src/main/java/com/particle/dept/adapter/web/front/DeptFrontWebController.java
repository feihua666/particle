package com.particle.dept.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dept.client.api.IDeptApplicationService;
import com.particle.dept.client.api.representation.IDeptRepresentationApplicationService;
import com.particle.dept.client.dto.command.DeptCreateCommand;
import com.particle.dept.client.dto.command.DeptUpdateCommand;
import com.particle.dept.client.dto.command.representation.DeptPageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Tag(name = "部门pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dept")
public class DeptFrontWebController extends AbstractBaseWebAdapter {


	@Autowired
	private IDeptApplicationService iDeptApplicationService;
	@Autowired
	private IDeptRepresentationApplicationService iDeptRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:dept:create')")
	@Operation(summary = "添加部门")
	@PostMapping("/create")
	@OpLog(name = "添加部门",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.create)
	public SingleResponse<DeptVO> create(@RequestBody DeptCreateCommand deptCreateCommand){
		return iDeptApplicationService.create(deptCreateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:dept:delete')")
	@Operation(summary = "删除部门")
	@DeleteMapping("/delete")
	@OpLog(name = "删除部门",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.delete)
	public SingleResponse<DeptVO> delete(@RequestBody IdCommand deleteCommand){
		return iDeptApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('front:web:dept:update')")
	@Operation(summary = "更新部门")
	@PutMapping("/update")
	@OpLog(name = "更新部门",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.update)
	public SingleResponse<DeptVO> update(@RequestBody DeptUpdateCommand deptUpdateCommand){
		return iDeptApplicationService.update(deptUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:dept:update')")
	@Operation(summary = "部门更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDeptRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:dept:detail')")
	@Operation(summary = "部门详情展示")
	@GetMapping("/detail")
	public SingleResponse<DeptVO> queryDetail(IdCommand detailCommand){
		return iDeptRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('front:web:dept:queryList')")
	@Operation(summary = "列表查询部门")
	@GetMapping("/list")
	public MultiResponse<DeptVO> queryList(DeptQueryListCommand deptQueryListCommand){
		return iDeptRepresentationApplicationService.queryList(deptQueryListCommand);
	}

	@PreAuthorize("hasAuthority('front:web:dept:pageQuery')")
	@Operation(summary = "分页查询部门")
	@GetMapping("/page")
	public PageResponse<DeptVO> pageQueryList(DeptPageQueryCommand deptPageQueryCommand){
		return iDeptRepresentationApplicationService.pageQuery(deptPageQueryCommand);
	}

}