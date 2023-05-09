package com.particle.oplog.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import com.particle.oplog.client.api.representation.IOpLogAuditDataRepresentationApplicationService;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 操作日志审计数据后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Api(tags = "操作日志审计数据pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/op_log_audit_data")
public class OpLogAuditDataAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpLogAuditDataApplicationService iOpLogAuditDataApplicationService;
	@Autowired
	private IOpLogAuditDataRepresentationApplicationService iOpLogAuditDataRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:opLogAuditData:delete')")
	@ApiOperation("删除操作日志审计数据")
	@DeleteMapping("/delete")
	public SingleResponse<OpLogAuditDataVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpLogAuditDataApplicationService.delete(deleteCommand);
	}


	@PreAuthorize("hasAuthority('admin:web:opLogAuditData:detail')")
	@ApiOperation("操作日志审计数据详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpLogAuditDataVO> queryDetail(IdCommand detailCommand){
		return iOpLogAuditDataRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:opLogAuditData:queryList')")
	@ApiOperation("列表查询操作日志审计数据")
	@GetMapping("/list")
	public MultiResponse<OpLogAuditDataVO> queryList(OpLogAuditDataQueryListCommand opLogAuditDataQueryListCommand){
		return iOpLogAuditDataRepresentationApplicationService.queryList(opLogAuditDataQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:opLogAuditData:pageQuery')")
	@ApiOperation("分页查询操作日志审计数据")
	@GetMapping("/page")
	public PageResponse<OpLogAuditDataVO> pageQueryList(OpLogAuditDataPageQueryCommand opLogAuditDataPageQueryCommand){
		return iOpLogAuditDataRepresentationApplicationService.pageQuery(opLogAuditDataPageQueryCommand);
	}

}