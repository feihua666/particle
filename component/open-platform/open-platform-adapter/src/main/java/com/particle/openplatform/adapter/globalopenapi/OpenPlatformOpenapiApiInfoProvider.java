package com.particle.openplatform.adapter.globalopenapi;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.global.openapi.api.GlobalOpenapiApiInfoProvider;
import com.particle.global.openapi.data.ApiFeeRuleInfo;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.ApiLogicRuleInfo;
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

import java.util.ArrayList;
import java.util.List;
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
		// 如果appid没有配置url这里会返回null
		OpenplatformAppOpenapiDO byAppIdAndOpenplatformOpenapiId = iOpenplatformAppOpenapiService.getByAppIdAndOpenplatformOpenapiId(appId, byUrl.getId());
		if (byAppIdAndOpenplatformOpenapiId == null) {
			return null;
		}
		// 接口逻辑调用规则配置信息
		ApiLogicRuleInfo apiLogicRuleInfo = getApiLogicRuleInfo(byUrl, byAppIdAndOpenplatformOpenapiId);
		// 计费信息
		ApiFeeRuleInfo apiFeeRuleInfo = getApiFeeRuleInfo(Optional.ofNullable(byAppIdAndOpenplatformOpenapiId.getOpenplatformOpenapiFeeId())
				.orElse(byUrl.getDefaultOpenplatformOpenapiFeeId()));
		// 应用级限制
		OpenapiLimitRuleInfo clientLimitRuleInfo = null;
		OpenplatformAppDO byAppId = iOpenplatformAppService.getByAppId(appId);
		if (byAppId.getOpenplatformOpenapiLimitRuleId() != null) {
			clientLimitRuleInfo = getOpenapiLimitRuleInfo(byAppId.getOpenplatformOpenapiLimitRuleId(), LimitRuleTarget.client_id);
		}
		// 应用和接口级限制
		OpenapiLimitRuleInfo clientAndOpenapiLimitRuleInfo = null;
		if (byAppIdAndOpenplatformOpenapiId.getOpenplatformOpenapiLimitRuleId() != null) {
			clientAndOpenapiLimitRuleInfo = getOpenapiLimitRuleInfo(byAppIdAndOpenplatformOpenapiId.getOpenplatformOpenapiLimitRuleId(),
					LimitRuleTarget.client_id_and_openapi);
		}

		return ApiInfo.create(apiUrl,
				byUrl.getCode(),
				byUrl.getPermissions(),
                apiLogicRuleInfo,
				apiFeeRuleInfo,
				clientLimitRuleInfo,
				clientAndOpenapiLimitRuleInfo);
	}

	/**
	 * 获取接口逻辑规则
	 * @param openplatformOpenapiDO
	 * @param openplatformAppOpenapiDO
	 * @return
	 */
	private ApiLogicRuleInfo getApiLogicRuleInfo(OpenplatformOpenapiDO openplatformOpenapiDO,OpenplatformAppOpenapiDO openplatformAppOpenapiDO) {
		ApiLogicRuleInfo apiLogicRuleInfo = null;
		String specifyProviderConfigJson = openplatformAppOpenapiDO.getSpecifyProviderConfigJson();
		if (StrUtil.isNotEmpty(specifyProviderConfigJson)) {
			// 指定供应商配置
			List<ApiLogicRuleInfo.SpecifyProviderConfig> specifyProviderConfigs = null;
			// 可用供应商配置
			List<ApiLogicRuleInfo.AvailableProviderConfig> availableProviderConfigs = null;
			// 指定供应商配置转对象
			JSONArray specifyProviderConfigsArray = JSONUtil.parseArray(specifyProviderConfigJson);
			specifyProviderConfigs = new ArrayList<>(specifyProviderConfigsArray.size());
			for (Object item : specifyProviderConfigsArray) {
				JSONObject itemObject = (JSONObject) item;
				ApiLogicRuleInfo.SpecifyProviderConfig specifyProviderConfig = new ApiLogicRuleInfo.SpecifyProviderConfig();
				fillAvailableProviderConfig(specifyProviderConfig, itemObject);

				specifyProviderConfig.setIsWhenDataLagNext(itemObject.getBool("isWhenDataLagNext"));
				specifyProviderConfig.setIsWhenDataNotFoundNext(itemObject.getBool("isWhenDataNotFoundNext"));
				specifyProviderConfig.setIsWhenDataExistNext(itemObject.getBool("isWhenDataExistNext"));
				specifyProviderConfig.setIsWhenErrorNext(itemObject.getBool("isWhenErrorNext"));
				specifyProviderConfig.setIsWarehouseResult(itemObject.getBool("isWarehouseResult"));
				specifyProviderConfig.setIsWarehouseAsync(itemObject.getBool("isWarehouseAsync"));
				specifyProviderConfigs.add(specifyProviderConfig);
			}
			// 可用供应商配置转对象
			String providerConfigJson = openplatformOpenapiDO.getProviderConfigJson();
			if (StrUtil.isNotEmpty(providerConfigJson)) {
				JSONArray availableProviderConfigsArray = JSONUtil.parseArray(providerConfigJson);
				availableProviderConfigs = new ArrayList<>(availableProviderConfigsArray.size());
				for (Object item : availableProviderConfigsArray) {
					JSONObject itemObject = (JSONObject) item;
					ApiLogicRuleInfo.AvailableProviderConfig availableProviderConfig = new ApiLogicRuleInfo.AvailableProviderConfig();
					fillAvailableProviderConfig(availableProviderConfig, itemObject);
					availableProviderConfigs.add(availableProviderConfig);
				}
			}
			apiLogicRuleInfo = ApiLogicRuleInfo.create(specifyProviderConfigs, availableProviderConfigs);

		}

		return apiLogicRuleInfo;
	}

	private void fillAvailableProviderConfig(ApiLogicRuleInfo.AvailableProviderConfig availableProviderConfig, JSONObject itemObject) {
		availableProviderConfig.setId(itemObject.getStr("id"));
		availableProviderConfig.setProviderCode(itemObject.getStr("openplatformProviderCode"));
		availableProviderConfig.setProviderName(itemObject.getStr("openplatformProviderName"));
		availableProviderConfig.setProviderApiVersion(itemObject.getStr("providerApiVersion"));
		availableProviderConfig.setDataLagGroovyScript(itemObject.getStr("dataLagGroovyScript"));
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
