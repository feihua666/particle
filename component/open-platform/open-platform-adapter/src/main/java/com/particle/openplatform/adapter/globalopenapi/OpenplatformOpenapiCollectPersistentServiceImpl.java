package com.particle.openplatform.adapter.globalopenapi;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.openapi.api.GlobalOpenapiCollectPersistentService;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.openplatform.domain.event.*;
import com.particle.openplatform.domain.openapirecord.gateway.OpenplatformOpenapiRecordGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	/**
	 * 将收集的数据整理成mq事件，并发布到mq
	 * @param openapiContext
	 */
	@Override
	public void save(OpenapiContext openapiContext) {
		// 调用记录
		OpenplatformOpenapiRecordDomainEventContentRecord openplatformOpenapiRecordDomainEventContentRecord = new OpenplatformOpenapiRecordDomainEventContentRecord();
		OpenplatformOpenapiRecordDomainEventContentRecordParam openplatformOpenapiRecordDomainEventContentRecordParam
				= OpenplatformOpenapiRecordDomainEventContentRecordParam.create(JsonTool.toJsonStr(openapiContext.getRequestParameterDTO()),
				parseObjToString(openapiContext.getResponseResult()));
		openplatformOpenapiRecordDomainEventContentRecord.setRecordParam(openplatformOpenapiRecordDomainEventContentRecordParam);
		String appId = openapiContext.getOpenapiClient().getClientId();
		openplatformOpenapiRecordDomainEventContentRecord.setAppId(appId);
		openplatformOpenapiRecordDomainEventContentRecord.setUserId(LoginUserTool.getLoginUserId());
		openplatformOpenapiRecordDomainEventContentRecord.setIsApp(appId != null);
		ApiInfo apiInfo = openapiContext.getApiInfo();
		if (apiInfo != null) {
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

		//	供应商调用记录
		OpenplatformOpenapiRecordDomainEventContentProviderRecord openplatformOpenapiRecordDomainEventContentProviderRecord = null;
		OpenplatformOpenapiRecordDomainEventContentRecordParam openplatformRecordDomainEventContentProviderRecordParam = null;
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = null;
		if (CollectionUtil.isNotEmpty(openapiContext.getProviderDTOS())) {
			providerRecords = new ArrayList<>(openapiContext.getProviderDTOS().size());

			for (OpenapiCollectProviderDTO providerDTO : openapiContext.getProviderDTOS()) {
				openplatformOpenapiRecordDomainEventContentProviderRecord = new OpenplatformOpenapiRecordDomainEventContentProviderRecord();
				openplatformRecordDomainEventContentProviderRecordParam
						= OpenplatformOpenapiRecordDomainEventContentRecordParam.create(parseObjToString(providerDTO.getRequestParam()),
						parseObjToString(providerDTO.getResponseResult()));
				openplatformOpenapiRecordDomainEventContentProviderRecord.setRecordParam(openplatformRecordDomainEventContentProviderRecordParam);

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


		OpenplatformOpenapiRecordDomainEventContent openplatformOpenapiRecordDomainEventContent = OpenplatformOpenapiRecordDomainEventContent.create(openplatformOpenapiRecordDomainEventContentRecord, providerRecords);

		OpenplatformOpenapiRecordDomainEvent openplatformOpenapiRecordDomainEvent = new OpenplatformOpenapiRecordDomainEvent(openplatformOpenapiRecordDomainEventContent);
		//	将该事件发布到mq并不直接保存
		openplatformOpenapiRecordGateway.sendDomainEventAsync(openplatformOpenapiRecordDomainEvent);
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
