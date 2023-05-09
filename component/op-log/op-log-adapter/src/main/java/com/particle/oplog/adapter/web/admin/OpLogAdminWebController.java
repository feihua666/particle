package com.particle.oplog.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.api.IOpLogApplicationService;
import com.particle.oplog.client.api.representation.IOpLogRepresentationApplicationService;
import com.particle.oplog.client.dto.command.representation.OpLogPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 操作日志后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Api(tags = "操作日志pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/op_log")
public class OpLogAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpLogApplicationService iOpLogApplicationService;
	@Autowired
	private IOpLogRepresentationApplicationService iOpLogRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:opLog:delete')")
	@ApiOperation("删除操作日志")
	@DeleteMapping("/delete")
	public SingleResponse<OpLogVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpLogApplicationService.delete(deleteCommand);
	}


	@PreAuthorize("hasAuthority('admin:web:opLog:detail')")
	@ApiOperation("操作日志详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpLogVO> queryDetail(IdCommand detailCommand){
		return iOpLogRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:opLog:queryList')")
	@ApiOperation("列表查询操作日志")
	@GetMapping("/list")
	public MultiResponse<OpLogVO> queryList(OpLogQueryListCommand opLogQueryListCommand){
		return iOpLogRepresentationApplicationService.queryList(opLogQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:opLog:pageQuery')")
	@ApiOperation("分页查询操作日志")
	@GetMapping("/page")
	public PageResponse<OpLogVO> pageQueryList(OpLogPageQueryCommand opLogPageQueryCommand){
		return iOpLogRepresentationApplicationService.pageQuery(opLogPageQueryCommand);
	}

}