package com.particle.openplatform.adapter.globalopenapi;

import com.particle.global.openapi.api.GlobalOpenapiApiInfoProvider;
import com.particle.global.openapi.data.ApiFeeRuleInfo;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.ApiRuleInfo;
import com.particle.global.openapi.data.OpenapiLimitRuleInfo;
import com.particle.global.openapi.enums.*;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppOpenapiService;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiFeeDO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiLimitRuleDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiFeeService;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiLimitRuleService;
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
	@Autowired
	private IOpenplatformOpenapiFeeService iOpenplatformOpenapiFeeService;
	@Autowired
	private IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService;
	@Autowired
	private IOpenplatformAppService iOpenplatformAppService;
	@Autowired
	private OpenplatformDictGateway openplatformDictGateway;
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
		// 计费信息
		ApiFeeRuleInfo apiFeeRuleInfo = getApiFeeRuleInfo(Optional.ofNullable(byAppIdAndOpenplatformOpenapiId.getOpenplatformOpenapiFeeId())
				.orElse(byUrl.getDefaultOpenplatformOpenapiFeeId()));

		// 应用级限制
		OpenapiLimitRuleInfo clientLimitRuleInfo = null;
		// 应用和接口级限制
		OpenapiLimitRuleInfo clientAndOpenapiLimitRuleInfo = null;

		OpenplatformAppDO byAppId = iOpenplatformAppService.getByAppId(appId);
		if (byAppId.getOpenplatformOpenapiLimitRuleId() != null) {
			clientLimitRuleInfo = getOpenapiLimitRuleInfo(byAppId.getOpenplatformOpenapiLimitRuleId(), LimitRuleTarget.client_id);
		}

		if (byAppIdAndOpenplatformOpenapiId.getOpenplatformOpenapiLimitRuleId() != null) {
			clientAndOpenapiLimitRuleInfo = getOpenapiLimitRuleInfo(byAppIdAndOpenplatformOpenapiId.getOpenplatformOpenapiLimitRuleId(),
					LimitRuleTarget.client_id_and_openapi);
		}

		return ApiInfo.create(apiUrl,
				byUrl.getCode(),
				byUrl.getPermissions(),
				apiRuleInfo,
				apiFeeRuleInfo,
				clientLimitRuleInfo,
				clientAndOpenapiLimitRuleInfo);
	}

	/**
	 * 获取限制规则
	 * @param openplatformOpenapiLimitRuleId
	 * @param limitRuleTarget
	 * @return
	 */
	private OpenapiLimitRuleInfo getOpenapiLimitRuleInfo(Long openplatformOpenapiLimitRuleId, LimitRuleTarget limitRuleTarget) {
		if (openplatformOpenapiLimitRuleId == null) {
			return null;
		}
		OpenplatformOpenapiLimitRuleDO byId = iOpenplatformOpenapiLimitRuleService.getById(openplatformOpenapiLimitRuleId);

		OpenapiLimitRuleInfo openapiLimitRuleInfo = mapLimitRule(byId);
		openapiLimitRuleInfo.setLimitRuleTarget(limitRuleTarget);
		return openapiLimitRuleInfo;
	}
	/**
	 * 获取费用规则
	 * @param openplatformOpenapiFeeId
	 * @return
	 */
	private ApiFeeRuleInfo getApiFeeRuleInfo(Long openplatformOpenapiFeeId){
		if (openplatformOpenapiFeeId == null) {
			return null;
		}
		OpenplatformOpenapiFeeDO byId = iOpenplatformOpenapiFeeService.getById(openplatformOpenapiFeeId);

		ApiFeeRuleInfo apiFeeRuleInfo = mapFeeRule(byId);
		return apiFeeRuleInfo;
	}

	/**
	 * 限制规则对象转换
	 * @param openplatformOpenapiLimitRuleDO
	 * @return
	 */
	private OpenapiLimitRuleInfo mapLimitRule(OpenplatformOpenapiLimitRuleDO openplatformOpenapiLimitRuleDO) {
		if (openplatformOpenapiLimitRuleDO == null) {
			return null;
		}
		OpenapiLimitRuleInfo openapiLimitRuleInfo = new OpenapiLimitRuleInfo();
		openapiLimitRuleInfo.setName(openplatformOpenapiLimitRuleDO.getName());

		String limitTypeStr = openplatformDictGateway.getDictValueById(openplatformOpenapiLimitRuleDO.getLimitTypeDictId());
		openapiLimitRuleInfo.setLimitRuleType(LimitRuleType.valueOf(limitTypeStr));

		openapiLimitRuleInfo.setLimitCount(openplatformOpenapiLimitRuleDO.getLimitCount());
		openapiLimitRuleInfo.setLimitFee(openplatformOpenapiLimitRuleDO.getLimitFee());

		String limitPeriodStr = openplatformDictGateway.getDictValueById(openplatformOpenapiLimitRuleDO.getLimitPeriodDictId());
		openapiLimitRuleInfo.setLimitRulePeriod(LimitRulePeriod.valueOf(limitPeriodStr));

		openapiLimitRuleInfo.setLimitRate(openplatformOpenapiLimitRuleDO.getLimitRate());

		openapiLimitRuleInfo.setWhiteIps(openplatformOpenapiLimitRuleDO.getWhiteIps());
		openapiLimitRuleInfo.setBlackIps(openplatformOpenapiLimitRuleDO.getBlackIps());

		return openapiLimitRuleInfo;

	}
	/**
	 * 计费规则对象转换
	 * @param openplatformOpenapiFeeDO
	 * @return
	 */
	private ApiFeeRuleInfo mapFeeRule(OpenplatformOpenapiFeeDO openplatformOpenapiFeeDO) {
		if (openplatformOpenapiFeeDO == null) {
			return null;
		}

		ApiFeeRuleInfo apiFeeRuleInfo = new ApiFeeRuleInfo();
		apiFeeRuleInfo.setPrice(openplatformOpenapiFeeDO.getPrice());

		String feeTypeStr = openplatformDictGateway.getDictValueById(openplatformOpenapiFeeDO.getFeeTypeDictId());
		apiFeeRuleInfo.setFeeRuleFeeType(FeeRuleFeeType.valueOf(feeTypeStr));

		String deduplicateTypeStr = openplatformDictGateway.getDictValueById(openplatformOpenapiFeeDO.getDeduplicateTypeDictId());
		apiFeeRuleInfo.setFeeRuleDeduplicateType(FeeRuleDeduplicateType.valueOf(deduplicateTypeStr));

		apiFeeRuleInfo.setDeduplicateCount(openplatformOpenapiFeeDO.getDeduplicateCount());
		apiFeeRuleInfo.setIsDeduplicateByParameter(openplatformOpenapiFeeDO.getIsDeduplicateByParameter());
		apiFeeRuleInfo.setIsCheckHasValue(openplatformOpenapiFeeDO.getIsCheckHasValue());
		apiFeeRuleInfo.setIsCheckHandleDuration(openplatformOpenapiFeeDO.getIsCheckHandleDuration());
		apiFeeRuleInfo.setHandleDuration(openplatformOpenapiFeeDO.getHandleDuration());
		return apiFeeRuleInfo;

	}
}
