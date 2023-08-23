package com.particle.openplatform.app.provider.executor.representation;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 开放平台开放接口供应商 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Component
@Validated
public class OpenplatformProviderQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderService iOpenplatformProviderService;
	private IOpenplatformOpenapiService iOpenplatformOpenapiService;

	/**
	 * 执行 开放平台开放接口供应商 列表查询指令
	 * @param openplatformProviderQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderVO> execute(@Valid OpenplatformProviderQueryListCommand openplatformProviderQueryListCommand) {
		if (openplatformProviderQueryListCommand.getOpenplatformOpenapiId() != null) {
			OpenplatformOpenapiDO byId = iOpenplatformOpenapiService.getById(openplatformProviderQueryListCommand.getOpenplatformOpenapiId());
			if (byId == null) {
				return MultiResponse.buildSuccess();
			}
			String openplatformProviderIds = byId.getOpenplatformProviderIds();
			if (StrUtil.isEmpty(openplatformProviderIds)) {
				return MultiResponse.buildSuccess();
			}
			List<Long> providerIds = Arrays.stream(openplatformProviderIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
			openplatformProviderQueryListCommand.setIds(providerIds);
		}
		List<OpenplatformProviderDO> openplatformProviderDO = iOpenplatformProviderService.list(openplatformProviderQueryListCommand);
		List<OpenplatformProviderVO> openplatformProviderVOs = OpenplatformProviderAppStructMapping.instance.openplatformProviderDOsToOpenplatformProviderVOs(openplatformProviderDO);
		return MultiResponse.of(openplatformProviderVOs);
	}
	/**
	 * 执行 开放平台开放接口供应商 分页查询指令
	 * @param openplatformProviderPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderVO> execute(@Valid OpenplatformProviderPageQueryCommand openplatformProviderPageQueryCommand) {
		Page<OpenplatformProviderDO> page = iOpenplatformProviderService.listPage(openplatformProviderPageQueryCommand);
		return OpenplatformProviderAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口供应商 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderDO byId = iOpenplatformProviderService.getById(detailCommand.getId());
		OpenplatformProviderVO openplatformProviderVO = OpenplatformProviderAppStructMapping.instance.openplatformProviderDOToOpenplatformProviderVO(byId);
		return SingleResponse.of(openplatformProviderVO);
	}
	/**
	 * 执行 开放平台开放接口供应商 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderDO byId = iOpenplatformProviderService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderVO openplatformProviderVO = OpenplatformProviderAppStructMapping.instance.openplatformProviderDOToOpenplatformProviderVO(byId);
		return SingleResponse.of(openplatformProviderVO);
	}

	@Autowired
	public void setIOpenplatformProviderService(IOpenplatformProviderService iOpenplatformProviderService) {
		this.iOpenplatformProviderService = iOpenplatformProviderService;
	}
	@Autowired
	public void setiOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
		this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
	}
}
