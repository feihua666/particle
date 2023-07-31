package com.particle.oauth2authorization.adapter.client.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.client.client.api.IOauth2RegisteredClientApplicationService;
import com.particle.oauth2authorization.client.client.api.representation.IOauth2RegisteredClientRepresentationApplicationService;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientCreateCommand;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientUpdateCommand;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientPageQueryCommand;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientQueryListCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * oauth2客户端后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Tag(name = "oauth2客户端pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/oauth2_registered_client")
public class Oauth2RegisteredClientAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOauth2RegisteredClientApplicationService iOauth2RegisteredClientApplicationService;
	@Autowired
	private IOauth2RegisteredClientRepresentationApplicationService iOauth2RegisteredClientRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:create')")
	@Operation(summary = "添加oauth2客户端")
	@PostMapping("/create")
	@OpLog(name = "添加oauth2客户端",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<Oauth2RegisteredClientVO> create(@RequestBody Oauth2RegisteredClientCreateCommand oauth2RegisteredClientCreateCommand){
		return iOauth2RegisteredClientApplicationService.create(oauth2RegisteredClientCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:delete')")
	@Operation(summary = "删除oauth2客户端")
	@DeleteMapping("/delete")
	@OpLog(name = "删除oauth2客户端",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<Oauth2RegisteredClientVO> delete(@RequestBody IdCommand deleteCommand){
		return iOauth2RegisteredClientApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:update')")
	@Operation(summary = "更新oauth2客户端")
	@PutMapping("/update")
	@OpLog(name = "更新oauth2客户端",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<Oauth2RegisteredClientVO> update(@RequestBody Oauth2RegisteredClientUpdateCommand oauth2RegisteredClientUpdateCommand){
		return iOauth2RegisteredClientApplicationService.update(oauth2RegisteredClientUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:update')")
	@Operation(summary = "oauth2客户端更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<Oauth2RegisteredClientVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOauth2RegisteredClientRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:detail')")
	@Operation(summary = "oauth2客户端详情展示")
	@GetMapping("/detail")
	public SingleResponse<Oauth2RegisteredClientVO> queryDetail(IdCommand detailCommand){
		return iOauth2RegisteredClientRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:queryList')")
	@Operation(summary = "列表查询oauth2客户端")
	@GetMapping("/list")
	public MultiResponse<Oauth2RegisteredClientVO> queryList(Oauth2RegisteredClientQueryListCommand oauth2RegisteredClientQueryListCommand){
		return iOauth2RegisteredClientRepresentationApplicationService.queryList(oauth2RegisteredClientQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:oauth2RegisteredClient:pageQuery')")
	@Operation(summary = "分页查询oauth2客户端")
	@GetMapping("/page")
	public PageResponse<Oauth2RegisteredClientVO> pageQueryList(Oauth2RegisteredClientPageQueryCommand oauth2RegisteredClientPageQueryCommand){
		return iOauth2RegisteredClientRepresentationApplicationService.pageQuery(oauth2RegisteredClientPageQueryCommand);
	}
}