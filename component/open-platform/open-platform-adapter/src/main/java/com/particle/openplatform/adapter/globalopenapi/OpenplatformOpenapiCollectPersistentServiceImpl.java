package com.particle.openplatform.adapter.globalopenapi;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.openapi.api.GlobalOpenapiCollectPersistentService;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.ApiFeeRuleInfo;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.openplatform.domain.enums.OpenPlatformDeduplicateType;
import com.particle.openplatform.domain.enums.OpenPlatformFeeType;
import com.particle.openplatform.domain.event.*;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeValue;
import com.particle.openplatform.domain.openapirecord.gateway.OpenplatformOpenapiRecordGateway;
import com.particle.openplatform.domain.providerrecord.gateway.OpenplatformProviderRecordGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>
 * 开放平台实现开放接口存储
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 17:08
 */
@Component
public class OpenplatformOpenapiCollectPersistentServiceImpl implements GlobalOpenapiCollectPersistentService {

	@Autowired
	private OpenplatformOpenapiRecordGateway openplatformOpenapiRecordGateway;
	@Autowired
	private OpenplatformProviderRecordGateway openplatformProviderRecordGateway;

	/**
	 * 将收集的数据整理成mq事件，并发布到mq
	 * @param openapiContext
	 */
	@Override
	public void save(OpenapiContext openapiContext) {
		// 开放接口调用记录
		OpenplatformOpenapiRecordDomainEventContentRecord record = record(openapiContext);
		//	供应商调用记录
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = providerRecords(openapiContext);

		OpenplatformOpenapiRecordDomainEventContent openplatformOpenapiRecordDomainEventContent = OpenplatformOpenapiRecordDomainEventContent.create(record, providerRecords);
		OpenplatformOpenapiRecordDomainEvent openplatformOpenapiRecordDomainEvent = new OpenplatformOpenapiRecordDomainEvent(openplatformOpenapiRecordDomainEventContent);
		//	将该事件发布到mq并不直接保存
		openplatformOpenapiRecordGateway.sendDomainEventAsync(openplatformOpenapiRecordDomainEvent);
	}

	@Override
	public void saveProvider(OpenapiContext openapiContext) {
		//	供应商调用记录
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = providerRecords(openapiContext);
		if (CollectionUtil.isEmpty(providerRecords)) {
			return;
		}

		OpenplatformOpenapiProviderRecordDomainEventContent openplatformOpenapiRecordDomainEventContent = OpenplatformOpenapiProviderRecordDomainEventContent.create(providerRecords);
		OpenplatformOpenapiProviderRecordDomainEvent openplatformOpenapiRecordDomainEvent = new OpenplatformOpenapiProviderRecordDomainEvent(openplatformOpenapiRecordDomainEventContent);
		//	将该事件发布到mq并不直接保存
		openplatformProviderRecordGateway.sendDomainEventAsync(openplatformOpenapiRecordDomainEvent);
	}

	/**
	 * 开放接口调用记录处理映射
	 * @param openapiContext
	 * @return
	 */
	private OpenplatformOpenapiRecordDomainEventContentRecord record(OpenapiContext openapiContext) {
		OpenplatformOpenapiRecordDomainEventContentRecord openplatformOpenapiRecordDomainEventContentRecord = new OpenplatformOpenapiRecordDomainEventContentRecord();

		openplatformOpenapiRecordDomainEventContentRecord.setId(openapiContext.getId());

		OpenplatformOpenapiRecordDomainEventContentRecordParam openplatformOpenapiRecordDomainEventContentRecordParam
				= OpenplatformOpenapiRecordDomainEventContentRecordParam.create(JsonTool.toJsonStr(openapiContext.getRequestParameterDTO()),
				parseObjToString(openapiContext.getResponseResult()));
		openplatformOpenapiRecordDomainEventContentRecord.setRecordParam(openplatformOpenapiRecordDomainEventContentRecordParam);
		String appId = openapiContext.getOpenapiClient().getClientId();
		openplatformOpenapiRecordDomainEventContentRecord.setAppId(appId);
		Long customerId = openapiContext.getOpenapiClient().getOwnerCustomerId();
		Long userId = openapiContext.getOpenapiClient().getOwnerUserId();
		openplatformOpenapiRecordDomainEventContentRecord.setUserId(userId != null ? userId : LoginUserTool.getLoginUserId());
		openplatformOpenapiRecordDomainEventContentRecord.setCustomerId(customerId);
		openplatformOpenapiRecordDomainEventContentRecord.setIsApp(appId != null);
		ApiInfo apiInfo = openapiContext.getApiInfo();
		ApiFeeRuleInfo apiFeeRuleInfo = null;
		if (apiInfo != null) {
			apiFeeRuleInfo = apiInfo.getApiFeeRuleInfo();
			openplatformOpenapiRecordDomainEventContentRecord.setRequestUrl(apiInfo.getApiUrl());
		}else {
			openplatformOpenapiRecordDomainEventContentRecord.setRequestUrl(openapiContext.getRequestUrl());
		}
		openplatformOpenapiRecordDomainEventContentRecord.setRequestTimestamp(openapiContext.getBasicHeaderDTO().getTimestamp());
		openplatformOpenapiRecordDomainEventContentRecord.setRequestNonce(openapiContext.getBasicHeaderDTO().getNonce());
		openplatformOpenapiRecordDomainEventContentRecord.setRequestSignature(openapiContext.getBasicHeaderDTO().getSignature());
		if (StrUtil.isNotEmpty(openplatformOpenapiRecordDomainEventContentRecordParam.getRequestParam())) {
			openplatformOpenapiRecordDomainEventContentRecord.setRequestParameterMd5(DigestUtil.md5Hex(openplatformOpenapiRecordDomainEventContentRecordParam.getRequestParam()));
		}
		openplatformOpenapiRecordDomainEventContentRecord.setRequestStartAt(openapiContext.getRequestStartAt());
		openplatformOpenapiRecordDomainEventContentRecord.setRequestEndAt(openapiContext.getRequestEndAt());


		if (StrUtil.isNotEmpty(openplatformOpenapiRecordDomainEventContentRecordParam.getResponseResult())) {
			openplatformOpenapiRecordDomainEventContentRecord.setResponseResultMd5(DigestUtil.md5Hex(openplatformOpenapiRecordDomainEventContentRecordParam.getResponseResult()));
		}
		openplatformOpenapiRecordDomainEventContentRecord.setTraceId(openapiContext.getTraceId());
		Integer handleDuration = (int)LocalDateTimeUtil.between(openapiContext.getRequestStartAt(),openapiContext.getRequestEndAt()).toMillis();
		openplatformOpenapiRecordDomainEventContentRecord.setHandleDuration(handleDuration);
		openplatformOpenapiRecordDomainEventContentRecord.setIsResponseHasEffectiveValue(Optional.ofNullable(openapiContext.getIsResponseHasEffectiveValue()).orElse(true));
		openplatformOpenapiRecordDomainEventContentRecord.setResponseHttpStatus(openapiContext.getResponseHttpStatus());
		openplatformOpenapiRecordDomainEventContentRecord.setResponseBusinessStatus(openapiContext.getResponseBusinessStatus());
		openplatformOpenapiRecordDomainEventContentRecord.setRemark(openapiContext.getRemark());
		OpenplatformOpenapiFeeValue openplatformOpenapiFeeValue = mapFeeValue(apiFeeRuleInfo);
		openplatformOpenapiRecordDomainEventContentRecord.setOpenplatformOpenapiFee(openplatformOpenapiFeeValue);
		return openplatformOpenapiRecordDomainEventContentRecord;
	}
	/**
	 * 供应商调用记录收集处理映射
	 * @param openapiContext
	 * @return
	 */
	private List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords(OpenapiContext openapiContext) {
		OpenplatformOpenapiRecordDomainEventContentProviderRecord openplatformOpenapiRecordDomainEventContentProviderRecord = null;
		OpenplatformOpenapiRecordDomainEventContentRecordParam openplatformRecordDomainEventContentProviderRecordParam = null;
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = null;
		if (CollectionUtil.isNotEmpty(openapiContext.getProviderDTOS())) {
			providerRecords = new ArrayList<>(openapiContext.getProviderDTOS().size());

			for (OpenapiCollectProviderDTO providerDTO : openapiContext.getProviderDTOS()) {
				openplatformOpenapiRecordDomainEventContentProviderRecord = new OpenplatformOpenapiRecordDomainEventContentProviderRecord();
				openplatformOpenapiRecordDomainEventContentProviderRecord.setOpenapiRecordId(openapiContext.getId());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setCustomerId(providerDTO.getCustomerId());
				String requestParamString = parseObjToString(providerDTO.getRequestParam());
				String queryString = providerDTO.getQueryString();
				String requestParam = null;
				if (StrUtil.isNotEmpty(queryString) && StrUtil.isNotEmpty(requestParamString)) {
					Map<String, String> requestParamMap = new HashMap<>(2);
					requestParamMap.put("requestParam", requestParamString);
					requestParamMap.put("queryString", queryString);
					requestParam = JsonTool.toJsonStr(requestParamMap);
				} else if(StrUtil.isNotEmpty(requestParamString)){
					requestParam = requestParamString;
				} else if(StrUtil.isNotEmpty(queryString)){
					requestParam = queryString;
				}
				openplatformRecordDomainEventContentProviderRecordParam
						= OpenplatformOpenapiRecordDomainEventContentRecordParam.create(requestParam,
						parseObjToString(providerDTO.getResponseResult()));
				openplatformOpenapiRecordDomainEventContentProviderRecord.setRecordParam(openplatformRecordDomainEventContentProviderRecordParam);

				openplatformOpenapiRecordDomainEventContentProviderRecord.setRequestName(providerDTO.getRequestName());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setRequestUrl(providerDTO.getRequestUrl());
				if (StrUtil.isNotEmpty(openplatformRecordDomainEventContentProviderRecordParam.getRequestParam())) {
					openplatformOpenapiRecordDomainEventContentProviderRecord.setRequestParameterMd5(DigestUtil.md5Hex(openplatformRecordDomainEventContentProviderRecordParam.getRequestParam()));
				}

				if (StrUtil.isNotEmpty(openplatformRecordDomainEventContentProviderRecordParam.getResponseResult())) {
					openplatformOpenapiRecordDomainEventContentProviderRecord.setResponseResultMd5(DigestUtil.md5Hex(openplatformRecordDomainEventContentProviderRecordParam.getResponseResult()));
				}

				openplatformOpenapiRecordDomainEventContentProviderRecord.setRequestStartAt(providerDTO.getRequestStartAt());

				openplatformOpenapiRecordDomainEventContentProviderRecord.setTraceId(openapiContext.getTraceId());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setHandleDuration(providerDTO.getHandleDuration());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setIsResponseHasEffectiveValue(Optional.ofNullable(providerDTO.getIsResponseHasEffectiveValue()).orElse(true));
				openplatformOpenapiRecordDomainEventContentProviderRecord.setResponseHttpStatus(providerDTO.getResponseHttpStatus());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setResponseBusinessStatus(providerDTO.getResponseBusinessStatus());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setProviderIdentifier(providerDTO.getProviderIdentifier());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setIsCacheHit(providerDTO.getIsCacheHit());
				openplatformOpenapiRecordDomainEventContentProviderRecord.setRemark(providerDTO.getRemark());
				providerRecords.add(openplatformOpenapiRecordDomainEventContentProviderRecord);

			}// end for
		}// end if

		return providerRecords;
	}
	/**
	 * 计费规则对象转换
	 * @param apiFeeRuleInfo
	 * @return
	 */
	private OpenplatformOpenapiFeeValue mapFeeValue(ApiFeeRuleInfo apiFeeRuleInfo) {
		if (apiFeeRuleInfo == null) {
			return null;
		}
		OpenplatformOpenapiFeeValue openplatformOpenapiFeeValue = new OpenplatformOpenapiFeeValue();
		openplatformOpenapiFeeValue.setPrice(apiFeeRuleInfo.getPrice());

		OpenPlatformFeeType openPlatformFeeType = OpenPlatformFeeType.valueOf(apiFeeRuleInfo.getFeeRuleFeeType().name());
		openplatformOpenapiFeeValue.setOpenPlatformFeeType(openPlatformFeeType);

		OpenPlatformDeduplicateType openPlatformDeduplicateType = OpenPlatformDeduplicateType.valueOf(apiFeeRuleInfo.getFeeRuleDeduplicateType().name());
		openplatformOpenapiFeeValue.setOpenPlatformDeduplicateType(openPlatformDeduplicateType);

		openplatformOpenapiFeeValue.setDeduplicateCount(apiFeeRuleInfo.getDeduplicateCount());
		openplatformOpenapiFeeValue.setIsDeduplicateByParameter(apiFeeRuleInfo.getIsDeduplicateByParameter());
		openplatformOpenapiFeeValue.setIsCheckHasValue(apiFeeRuleInfo.getIsCheckHasValue());
		openplatformOpenapiFeeValue.setIsCheckHandleDuration(apiFeeRuleInfo.getIsCheckHandleDuration());
		openplatformOpenapiFeeValue.setHandleDuration(apiFeeRuleInfo.getHandleDuration());
		return openplatformOpenapiFeeValue;

	}

	/**
	 * 将对象转为字符串
	 * @param obj
	 * @return
	 */
	public String parseObjToString(Object obj) {
		if (obj == null) {
			return null;
		}
		if(obj instanceof String ){
			return ((String) obj);
		}
		try {
			return JsonTool.toJsonStr(obj);
		} catch (Exception e) {
			return obj.toString();
		}
	}

}
