package com.particle.config.adapter.system.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.config.app.system.structmapping.SystemConfigAppStructMapping;
import com.particle.config.client.system.api.ISystemConfigApplicationService;
import com.particle.config.adapter.feign.client.system.rpc.SystemConfigRpcFeignClient;
import com.particle.config.client.system.api.representation.ISystemConfigRepresentationApplicationService;
import com.particle.config.client.system.dto.command.SystemConfigCreateCommand;
import com.particle.config.client.system.dto.command.SystemConfigUpdateCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统参数配置远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Tag(name = "系统参数配置远程调用相关接口")
@RestController
@RequestMapping("/rpc/system_config")
public class SystemConfigRpcController extends AbstractBaseRpcAdapter implements SystemConfigRpcFeignClient  {

	@Autowired
	private ISystemConfigApplicationService iSystemConfigApplicationService;
	@Autowired
	private ISystemConfigRepresentationApplicationService iSystemConfigRepresentationApplicationService;

	@Autowired
	private ISystemConfigService iSystemConfigService;

	@Operation(summary = "添加系统参数配置")
	@PostMapping("/create")
	public SingleResponse<SystemConfigVO> create(@RequestBody SystemConfigCreateCommand systemConfigCreateCommand){
		return iSystemConfigApplicationService.create(systemConfigCreateCommand);
	}

	@Operation(summary = "删除系统参数配置")
	@DeleteMapping("/delete")
	public SingleResponse<SystemConfigVO> delete(@RequestBody IdCommand deleteCommand){
		return iSystemConfigApplicationService.delete(deleteCommand);
	}

	@Operation(summary = "更新系统参数配置")
	@PutMapping("/update")
	public SingleResponse<SystemConfigVO> update(@RequestBody SystemConfigUpdateCommand systemConfigUpdateCommand){
		return iSystemConfigApplicationService.update(systemConfigUpdateCommand);
	}

	@Operation(summary = "系统参数配置更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<SystemConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iSystemConfigRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@Operation(summary = "系统参数配置详情展示")
	@GetMapping("/detail")
	public SingleResponse<SystemConfigVO> queryDetail(IdCommand detailCommand){
		return iSystemConfigRepresentationApplicationService.queryDetail(detailCommand);
	}

	@Operation(summary = "列表查询系统参数配置")
	@GetMapping("/list")
	public MultiResponse<SystemConfigVO> queryList(SystemConfigQueryListCommand systemConfigQueryListCommand){
		return iSystemConfigRepresentationApplicationService.queryList(systemConfigQueryListCommand);
	}

	@Operation(summary = "分页查询系统参数配置")
	@GetMapping("/page")
	public PageResponse<SystemConfigVO> pageQueryList(SystemConfigPageQueryCommand systemConfigPageQueryCommand){
		return iSystemConfigRepresentationApplicationService.pageQuery(systemConfigPageQueryCommand);
	}


	@Operation(summary = "根据code查询系统配置")
	@Override
	public SingleResponse<SystemConfigVO> queryByCode(String code) {
		SystemConfigDO byCode = iSystemConfigService.getByCode(code);
		return SingleResponse.of(SystemConfigAppStructMapping.instance.systemConfigDOToSystemConfigVO(byCode));
	}

	@Operation(summary = "根据tag查询系统配置")
	@Override
	public MultiResponse<SystemConfigVO> queryByTag(String tag) {
		List<SystemConfigDO> byTag = iSystemConfigService.getByTag(tag);

		List<SystemConfigVO> systemConfigVOS = SystemConfigAppStructMapping.instance.systemConfigDOsToSystemConfigVOs(byTag);
		return MultiResponse.of(systemConfigVOS);
	}
}