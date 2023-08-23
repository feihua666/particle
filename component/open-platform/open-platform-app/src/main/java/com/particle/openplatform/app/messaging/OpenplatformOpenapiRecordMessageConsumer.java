package com.particle.openplatform.app.messaging;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.event.TemplatingDomainMessageEvent;
import com.particle.global.messaging.event.messaging.CloudStreamConsumer;
import com.particle.global.messaging.event.messaging.MessageConsumer;
import com.particle.global.tool.json.JsonTool;
import com.particle.openplatform.domain.event.*;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordParamService;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordParamService;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 开放平台调用记录事件消费
 * </p>
 *
 * @author yangwei
 * @since 2023-08-21 11:22
 */
@Transactional
@Slf4j
@Component
public class OpenplatformOpenapiRecordMessageConsumer implements Consumer<OpenplatformOpenapiRecordDomainEvent>, MessageConsumer {

	@Autowired
	private IOpenplatformAppService iOpenplatformAppService;
	@Autowired
	private IOpenplatformOpenapiService iOpenplatformOpenapiService;

	@Autowired
	private IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService;
	@Autowired
	private IOpenplatformOpenapiRecordParamService iOpenplatformOpenapiRecordParamService;

	@Autowired
	private IOpenplatformProviderService iOpenplatformProviderService;

	@Autowired
	private IOpenplatformProviderRecordService iOpenplatformProviderRecordService;
	@Autowired
	private IOpenplatformProviderRecordParamService iOpenplatformProviderRecordParamService;

	@Override
	public void accept(OpenplatformOpenapiRecordDomainEvent openplatformOpenapiRecordDomainEvent) {

		log.debug("received openplatformRecordDomainEvent message. content={}", JsonTool.toJsonStr(openplatformOpenapiRecordDomainEvent));
		try {
			doConsume(openplatformOpenapiRecordDomainEvent);
		} catch (Throwable e) {
			log.error("consume openplatformRecordDomainEvent message error log only,event={}",JsonTool.toJsonStr(openplatformOpenapiRecordDomainEvent),e);
			throw e;
		}

	}

	/**
	 * 消费逻辑处理
	 * @param openplatformOpenapiRecordDomainEvent
	 */
	private void doConsume(OpenplatformOpenapiRecordDomainEvent openplatformOpenapiRecordDomainEvent) {

		OpenplatformOpenapiRecordDomainEventContent data = openplatformOpenapiRecordDomainEvent.getData();

		//	调用记录
		OpenplatformOpenapiRecordDomainEventContentRecord contentRecord = data.getRecord();
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = data.getProviderRecords();
		boolean hasProviderRecords = CollectionUtil.isNotEmpty(providerRecords);

		OpenplatformAppDO openplatformAppDO = iOpenplatformAppService.getByAppId(contentRecord.getAppId());
		Long ownerCustomerId = openplatformAppDO.getOwnerCustomerId();

		OpenplatformOpenapiRecordDO openplatformOpenapiRecordDO = new OpenplatformOpenapiRecordDO();

		openplatformOpenapiRecordDO.setOpenplatformAppId(openplatformAppDO.getId());
		openplatformOpenapiRecordDO.setOpenplatformOpenapiId(openplatformAppDO.getId());
		openplatformOpenapiRecordDO.setAppId(contentRecord.getAppId());
		openplatformOpenapiRecordDO.setUserId(contentRecord.getUserId());
		openplatformOpenapiRecordDO.setIsApp(contentRecord.getIsApp());
		openplatformOpenapiRecordDO.setCustomerId(ownerCustomerId);
		if (StrUtil.isNotEmpty(contentRecord.getRequestUrl())) {
			OpenplatformOpenapiDO byUrl = iOpenplatformOpenapiService.getByUrl(contentRecord.getRequestUrl());
			if (byUrl != null) {
				openplatformOpenapiRecordDO.setOpenplatformOpenapiId(byUrl.getId());
			}
		}
		openplatformOpenapiRecordDO.setRequestUrl(contentRecord.getRequestUrl());
		openplatformOpenapiRecordDO.setRequestTimestamp(contentRecord.getRequestTimestamp());
		openplatformOpenapiRecordDO.setRequestNonce(contentRecord.getRequestNonce());
		openplatformOpenapiRecordDO.setRequestSignature(contentRecord.getRequestSignature());
		openplatformOpenapiRecordDO.setRequestParameterMd5(contentRecord.getRequestParameterMd5());
		openplatformOpenapiRecordDO.setResponseResultMd5(contentRecord.getResponseResultMd5());
		openplatformOpenapiRecordDO.setTraceId(contentRecord.getTraceId());
		openplatformOpenapiRecordDO.setHandleDuration(contentRecord.getHandleDuration());
		openplatformOpenapiRecordDO.setIsResponseHasEffectiveValue(contentRecord.getIsResponseHasEffectiveValue());
		openplatformOpenapiRecordDO.setResponseHttpStatus(contentRecord.getResponseHttpStatus());
		openplatformOpenapiRecordDO.setResponseBusinessStatus(contentRecord.getResponseBusinessStatus());
		openplatformOpenapiRecordDO.setIsExistProviderRecord(hasProviderRecords);
		openplatformOpenapiRecordDO.setRemark(contentRecord.getRemark());

		// 保存
		iOpenplatformOpenapiRecordService.save(openplatformOpenapiRecordDO);
		Long openplatformOpenapiRecordDOId = openplatformOpenapiRecordDO.getId();

		// 调用记录参数
		OpenplatformOpenapiRecordDomainEventContentRecordParam recordParam = contentRecord.getRecordParam();
		if (recordParam != null) {
			OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamDO = new OpenplatformOpenapiRecordParamDO();
			openplatformOpenapiRecordParamDO.setOpenplatformOpenapiRecordId(openplatformOpenapiRecordDOId);
			openplatformOpenapiRecordParamDO.setRequestParam(recordParam.getRequestParam());
			openplatformOpenapiRecordParamDO.setResponseResult(recordParam.getResponseResult());
			iOpenplatformOpenapiRecordParamService.save(openplatformOpenapiRecordParamDO);
		}

		//	供应商调用记录
		if (hasProviderRecords) {
			for (OpenplatformOpenapiRecordDomainEventContentProviderRecord providerRecord : providerRecords) {
				OpenplatformProviderRecordDO openplatformProviderRecordDO = new OpenplatformProviderRecordDO();

				openplatformProviderRecordDO.setOpenplatformOpenapiRecordId(openplatformOpenapiRecordDOId);
				openplatformProviderRecordDO.setCustomerId(ownerCustomerId);
				openplatformProviderRecordDO.setRequestUrl(providerRecord.getRequestUrl());
				openplatformProviderRecordDO.setRequestParameterMd5(providerRecord.getRequestParameterMd5());
				openplatformProviderRecordDO.setResponseResultMd5(providerRecord.getResponseResultMd5());
				openplatformProviderRecordDO.setTraceId(providerRecord.getTraceId());
				openplatformProviderRecordDO.setHandleDuration(providerRecord.getHandleDuration());
				openplatformProviderRecordDO.setIsResponseHasEffectiveValue(providerRecord.getIsResponseHasEffectiveValue());
				openplatformProviderRecordDO.setResponseHttpStatus(providerRecord.getResponseHttpStatus());
				openplatformProviderRecordDO.setResponseBusinessStatus(providerRecord.getResponseBusinessStatus());

				// 应用商信息
				String providerIdentifier = providerRecord.getProviderIdentifier();
				if (StrUtil.isNotEmpty(providerIdentifier)) {
					OpenplatformProviderDO openplatformProviderDO = iOpenplatformProviderService.getByCode(providerIdentifier);
					if (openplatformProviderDO == null) {
						try {

							/**
							 * 尝试该值是否为数据查询供应商id，兼容数据查询数据收集
							 * 参见：{@link com.particle.dataquery.DataqueryAutoConfiguration.OpenapiConfiguration#executorInfrastructureListener()}
							 */
							long dataQueryProviderId = Long.parseLong(providerIdentifier);
							openplatformProviderDO = iOpenplatformProviderService.getBydataQueryProviderId(dataQueryProviderId);
						} catch (NumberFormatException e) {

						}
					}
					if (openplatformProviderDO != null) {
						openplatformProviderRecordDO.setOpenplatformProviderId(openplatformProviderDO.getId());
						openplatformProviderRecordDO.setDataQueryProviderId(openplatformProviderDO.getDataQueryProviderId());
					}
				}
				openplatformProviderRecordDO.setRemark(providerRecord.getRemark());

				iOpenplatformProviderRecordService.save(openplatformProviderRecordDO);
				Long openplatformProviderRecordDOId = openplatformProviderRecordDO.getId();

				OpenplatformOpenapiRecordDomainEventContentRecordParam providerRecordParam = providerRecord.getRecordParam();
				if (providerRecordParam != null) {
					OpenplatformProviderRecordParamDO openplatformProviderRecordParamDO = new OpenplatformProviderRecordParamDO();
					openplatformProviderRecordParamDO.setOpenplatformProviderRecordId(openplatformProviderRecordDOId);
					openplatformProviderRecordParamDO.setRequestParam(providerRecordParam.getRequestParam());
					openplatformProviderRecordParamDO.setResponseResult(providerRecordParam.getResponseResult());
					iOpenplatformProviderRecordParamService.save(openplatformProviderRecordParamDO);
				}
			}
		}
	}
}
