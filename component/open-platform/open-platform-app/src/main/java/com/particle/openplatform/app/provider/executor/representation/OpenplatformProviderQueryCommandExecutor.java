package com.particle.openplatform.app.provider.executor.representation;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
			String providerConfigJson = byId.getProviderConfigJson();
			if (StrUtil.isEmpty(providerConfigJson)) {
				return MultiResponse.buildSuccess();
			}
			JSONArray providerConfigJsons = JSONUtil.parseArray(providerConfigJson);
            if (CollectionUtil.isEmpty(providerConfigJsons)) {
				return MultiResponse.buildSuccess();
            }
			List<Long> providerIds = new ArrayList<>(providerConfigJsons.size());
			for (Object configJson : providerConfigJsons) {
				// openplatformProviderId 在前端有写，数据存储为json就是这个字段，参见 openplatformOpenapiManage.ts 中 供应商配置
				Long providerId = ((JSONObject) configJson).getLong("openplatformProviderId");
				providerIds.add(providerId);
			}
			if (CollectionUtil.isEmpty(providerIds)) {
				return MultiResponse.buildSuccess();
			}
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
