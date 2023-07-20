package com.particle.lowcode.adapter.generator.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.lowcode.client.generator.api.representation.ILowcodeDatasourceRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceUpdateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourcePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourceQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
 * 低代码数据源后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Tag(name = "低代码数据源pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/lowcode-datasource")
public class LowcodeDatasourceAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;
	@Autowired
	private ILowcodeDatasourceRepresentationApplicationService iLowcodeDatasourceRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:create')")
	@Operation(summary = "添加低代码数据源")
	@PostMapping("/create")
	@OpLog(name = "添加低代码数据源",module = OpLogConstants.Module.lowCode,type = OpLogConstants.Type.create)
	public SingleResponse<LowcodeDatasourceVO> create(@RequestBody LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand){
		return iLowcodeDatasourceApplicationService.create(lowcodeDatasourceCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:delete')")
	@Operation(summary = "删除低代码数据源")
	@DeleteMapping("/delete")
	@OpLog(name = "删除低代码数据源",module = OpLogConstants.Module.lowCode,type = OpLogConstants.Type.delete)
	public SingleResponse<LowcodeDatasourceVO> delete(@RequestBody IdCommand deleteCommand){
		return iLowcodeDatasourceApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:update')")
	@Operation(summary = "更新低代码数据源")
	@PutMapping("/update")
	@OpLog(name = "更新低代码数据源",module = OpLogConstants.Module.lowCode,type = OpLogConstants.Type.update)
	public SingleResponse<LowcodeDatasourceVO> update(@RequestBody LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand){
		return iLowcodeDatasourceApplicationService.update(lowcodeDatasourceUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:update')")
	@Operation(summary = "低代码数据源更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<LowcodeDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iLowcodeDatasourceRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:detail')")
	@Operation(summary = "低代码数据源详情展示")
	@GetMapping("/detail")
	public SingleResponse<LowcodeDatasourceVO> queryDetail(IdCommand detailCommand){
		return iLowcodeDatasourceRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:queryList')")
	@Operation(summary = "列表查询低代码数据源")
	@GetMapping("/list")
	public MultiResponse<LowcodeDatasourceVO> queryList(LowcodeDatasourceQueryListCommand lowcodeDatasourceQueryListCommand){
		return iLowcodeDatasourceRepresentationApplicationService.queryList(lowcodeDatasourceQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:pageQuery')")
	@Operation(summary = "分页查询低代码数据源")
	@GetMapping("/page")
	public PageResponse<LowcodeDatasourceVO> pageQueryList(LowcodeDatasourcePageQueryCommand lowcodeDatasourcePageQueryCommand){
		return iLowcodeDatasourceRepresentationApplicationService.pageQuery(lowcodeDatasourcePageQueryCommand);
	}

}