package com.particle.dream.adapter.ssq.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dream.client.ssq.api.ISsqCodeApplicationService;
import com.particle.dream.client.ssq.api.representation.ISsqCodeRepresentationApplicationService;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodePageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeVO;
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
 * 双色球号码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Tag(name = "双色球号码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/ssq_code")
public class SsqCodeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ISsqCodeApplicationService iSsqCodeApplicationService;
	@Autowired
	private ISsqCodeRepresentationApplicationService iSsqCodeRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:ssqCode:detail')")
	@Operation(summary = "双色球号码详情展示")
	@GetMapping("/detail")
	public SingleResponse<SsqCodeVO> queryDetail(IdCommand detailCommand){
		return iSsqCodeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCode:queryList')")
	@Operation(summary = "列表查询双色球号码")
	@GetMapping("/list")
	public MultiResponse<SsqCodeVO> queryList(SsqCodeQueryListCommand ssqCodeQueryListCommand){
		return iSsqCodeRepresentationApplicationService.queryList(ssqCodeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:ssqCode:pageQuery')")
	@Operation(summary = "分页查询双色球号码")
	@GetMapping("/page")
	public PageResponse<SsqCodeVO> pageQueryList(SsqCodePageQueryCommand ssqCodePageQueryCommand){
		return iSsqCodeRepresentationApplicationService.pageQuery(ssqCodePageQueryCommand);
	}

	/**
	 * 主要用于当库里为空时初始化
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:ssqCode:allCodeInit')")
	@Operation(summary = "初始化全部双色球号码")
	@PostMapping("/allCodeInit")
	@OpLog(name = "初始化全部双色球号码",module = OpLogConstants.Module.dream,type = OpLogConstants.Type.create,ignoreDataAuditPublish = true)
	public Response allCodeInit(){
		return iSsqCodeApplicationService.allCodeInit();
	}

	/**
	 * 主要用于已经初始化完成后，但可能逻辑有错误，需要重新跑
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:ssqCode:allCodeUpdate')")
	@Operation(summary = "更新全部双色球号码")
	@PutMapping("/allCodeUpdate")
	@OpLog(name = "更新全部双色球号码",module = OpLogConstants.Module.dream,type = OpLogConstants.Type.update,ignoreDataAuditPublish = true)
	public Response allCodeUpdate(){
		return iSsqCodeApplicationService.allCodeUpdate();
	}
	/**
	 * 停止初始化添加 或 更新
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:ssqCode:allCodeStop')")
	@Operation(summary = "停止初始化或更新全部双色球号码")
	@PutMapping("/allCodeStop")
	@OpLog(name = "停止初始化或更新全部双色球号码",module = OpLogConstants.Module.dream,type = OpLogConstants.Type.update)
	public Response allCodeStop(){
		return iSsqCodeApplicationService.allCodeStop();
	}
}