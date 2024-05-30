package com.particle.dream.adapter.ssq.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dream.client.ssq.api.ISsqCodeOpenedApplicationService;
import com.particle.dream.client.ssq.api.representation.ISsqCodeOpenedRepresentationApplicationService;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedPageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:allCodeInit')")
	@Operation(summary = "初始化所有双色球开奖号码")
	@PostMapping("/allCodeInit")
	@OpLog(name = "初始化所有双色球开奖号码",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create,ignoreDataAuditPublish = true)
	public Response allCodeInit(){
		return iSsqCodeOpenedApplicationService.allCodeInit();
	}

	/**
	 * 停止初始化添加
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:ssqCodeOpened:allCodeStop')")
	@Operation(summary = "停止初始化所有双色球开奖号码")
	@PutMapping("/allCodeStop")
	@OpLog(name = "停止初始化所有双色球开奖号码",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update,ignoreDataAuditPublish = true)
	public Response allCodeStop(){
		return iSsqCodeOpenedApplicationService.allCodeStop();
	}
}