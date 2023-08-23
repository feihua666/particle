package com.particle.openplatform.adapter.globalopenapi;

import com.particle.global.openapi.api.GlobalOpenapiApiInfoProvider;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.ApiRuleInfo;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppOpenapiService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p>
 * 开放平台支持的
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 11:30
 */
@Component
public class OpenPlatformOpenapiApiInfoProvider implements GlobalOpenapiApiInfoProvider {

	@Autowired
	private IOpenplatformOpenapiService iOpenplatformOpenapiService;
	@Autowired
	private IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService;
	@Autowired
	private IOpenplatformProviderService iOpenplatformProviderService;

	@Override
	public ApiInfo getApiInfo(String apiUrl,String appId) {
		OpenplatformOpenapiDO byUrl = iOpenplatformOpenapiService.getByUrl(apiUrl);
		if (byUrl == null) {
			return null;
		}
		ApiRuleInfo apiRuleInfo = null;
		// 如果appid没有配置url这里会返回null
		OpenplatformAppOpenapiDO byAppIdAndOpenplatformOpenapiId = iOpenplatformAppOpenapiService.getByAppIdAndOpenplatformOpenapiId(appId, byUrl.getId());
		if (byAppIdAndOpenplatformOpenapiId == null) {
			return null;
		}
		if (byAppIdAndOpenplatformOpenapiId.getOpenplatformProviderId() != null) {
			OpenplatformProviderDO byId = iOpenplatformProviderService.getById(byAppIdAndOpenplatformOpenapiId.getOpenplatformProviderId());
			apiRuleInfo = Optional.ofNullable(byId)
					.map(item -> ApiRuleInfo.create(item.getCode())).orElse(null);
		}

		return ApiInfo.create(apiUrl,byUrl.getCode(),byUrl.getPermissions(),apiRuleInfo);
	}
}
