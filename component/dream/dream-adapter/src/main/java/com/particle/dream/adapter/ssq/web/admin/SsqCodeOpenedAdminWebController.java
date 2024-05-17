package com.particle.dream.adapter.ssq.web.admin;

import com.particle.dream.client.ssq.api.ISsqCodeOpenedApplicationService;
import com.particle.dream.client.ssq.api.representation.ISsqCodeOpenedRepresentationApplicationService;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedCreateCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedUpdateCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedPageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedQueryListCommand;
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
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 双色球开奖后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Tag(name = "双色球开奖pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/ssq_code_opened")
public class SsqCodeOpenedAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ISsqCodeOpenedApplicationService iSsqCodeOpenedApplicationService;
	@Autowired
	private ISsqCodeOpenedRepresentationApplicationService iSsqCodeOpenedRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:create')")
	@Operation(summary = "添加双色球开奖")
	@PostMapping("/create")
	@OpLog(name = "添加双色球开奖",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<SsqCodeOpenedVO> create(@RequestBody SsqCodeOpenedCreateCommand ssqCodeOpenedCreateCommand){
		return iSsqCodeOpenedApplicationService.create(ssqCodeOpenedCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:delete')")
	@Operation(summary = "删除双色球开奖")
	@DeleteMapping("/delete")
	@OpLog(name = "删除双色球开奖",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<SsqCodeOpenedVO> delete(@RequestBody IdCommand deleteCommand){
		return iSsqCodeOpenedApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:update')")
	@Operation(summary = "更新双色球开奖")
	@PutMapping("/update")
	@OpLog(name = "更新双色球开奖",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<SsqCodeOpenedVO> update(@RequestBody SsqCodeOpenedUpdateCommand ssqCodeOpenedUpdateCommand){
		return iSsqCodeOpenedApplicationService.update(ssqCodeOpenedUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:update')")
	@Operation(summary = "双色球开奖更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<SsqCodeOpenedVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iSsqCodeOpenedRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:detail')")
	@Operation(summary = "双色球开奖详情展示")
	@GetMapping("/detail")
	public SingleResponse<SsqCodeOpenedVO> queryDetail(IdCommand detailCommand){
		return iSsqCodeOpenedRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:queryList')")
	@Operation(summary = "列表查询双色球开奖")
	@GetMapping("/list")
	public MultiResponse<SsqCodeOpenedVO> queryList(SsqCodeOpenedQueryListCommand ssqCodeOpenedQueryListCommand){
		return iSsqCodeOpenedRepresentationApplicationService.queryList(ssqCodeOpenedQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:pageQuery')")
	@Operation(summary = "分页查询双色球开奖")
	@GetMapping("/page")
	public PageResponse<SsqCodeOpenedVO> pageQueryList(SsqCodeOpenedPageQueryCommand ssqCodeOpenedPageQueryCommand){
		return iSsqCodeOpenedRepresentationApplicationService.pageQuery(ssqCodeOpenedPageQueryCommand);
	}

}