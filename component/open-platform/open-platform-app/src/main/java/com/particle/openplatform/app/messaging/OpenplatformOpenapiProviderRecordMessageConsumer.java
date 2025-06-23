package com.particle.openplatform.app.messaging;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.messaging.event.messaging.MessageConsumer;
import com.particle.global.tool.json.JsonTool;
import com.particle.openplatform.domain.enums.OpenFlatformFeeReason;
import com.particle.openplatform.domain.event.OpenplatformOpenapiProviderRecordDomainEvent;
import com.particle.openplatform.domain.event.OpenplatformOpenapiProviderRecordDomainEventContent;
import com.particle.openplatform.domain.event.OpenplatformOpenapiRecordDomainEventContentProviderRecord;
import com.particle.openplatform.domain.event.OpenplatformOpenapiRecordDomainEventContentRecordParam;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
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
import java.util.Optional;
import java.util.function.Consumer;

/**
 * <p>
 * 开放平台供应商调用记录事件消费
 * </p>
 *
 * @author yangwei
 * @since 2025-06-23 15:12:11
 */
@Transactional
@Slf4j
@Component
public class OpenplatformOpenapiProviderRecordMessageConsumer implements Consumer<OpenplatformOpenapiProviderRecordDomainEvent>, MessageConsumer {

	/**
	 * 缓存8小时
	 */
	private static final TimedCache<OpenFlatformFeeReason, Long> openFlatformFeeReasonDictIdCache = CacheUtil.newTimedCache(1000 * 60 * 60 * 8);

	@Autowired
	private IOpenplatformProviderService iOpenplatformProviderService;
	@Autowired
	private IOpenplatformProviderRecordService iOpenplatformProviderRecordService;
	@Autowired
	private IOpenplatformProviderRecordParamService iOpenplatformProviderRecordParamService;
	@Autowired
	private OpenplatformDictGateway openplatformDictGateway;

	@Autowired
	private IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService;
	@Override
	public void accept(OpenplatformOpenapiProviderRecordDomainEvent openplatformOpenapiProviderRecordDomainEvent) {
		log.debug("received openplatformProviderRecordDomainEvent message. content={}", JsonTool.toJsonStr(openplatformOpenapiProviderRecordDomainEvent));
		try {
			doConsume(openplatformOpenapiProviderRecordDomainEvent);
		} catch (Throwable e) {
			log.error("consume openplatformProviderRecordDomainEvent message error log only,event={}",JsonTool.toJsonStr(openplatformOpenapiProviderRecordDomainEvent),e);
			throw e;
		}

	}

	/**
	 * 消费逻辑处理
	 * @param openplatformOpenapiProviderRecordDomainEvent
	 */
	private void doConsume(OpenplatformOpenapiProviderRecordDomainEvent openplatformOpenapiProviderRecordDomainEvent) {
		OpenplatformOpenapiProviderRecordDomainEventContent data = openplatformOpenapiProviderRecordDomainEvent.getData();
		// 供应商信息
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = data.getProviderRecords();
		// 标识是否存在供应商记录
		boolean hasProviderRecords = CollectionUtil.isNotEmpty(providerRecords);

		//	供应商调用记录
		if (hasProviderRecords) {
			Long openplatformOpenapiRecordDOId = providerRecords.get(0).getOpenapiRecordId();
			Long ownerCustomerId = providerRecords.get(0).getCustomerId();
			saveProviderRecord(providerRecords, openplatformOpenapiRecordDOId, ownerCustomerId);
		}

	}

	/**
	 * 保存供应商记录
	 * @param providerRecords
	 * @param openplatformOpenapiRecordDOId
	 * @param ownerCustomerId
	 */
	protected void saveProviderRecord(List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords,
									Long openplatformOpenapiRecordDOId,Long ownerCustomerId) {
		for (OpenplatformOpenapiRecordDomainEventContentProviderRecord providerRecord : providerRecords) {
			// 映射
			OpenplatformProviderRecordDO openplatformProviderRecordDO = mappingOpenplatformProviderRecordDO(providerRecord, openplatformOpenapiRecordDOId, ownerCustomerId);
			iOpenplatformProviderRecordService.save(openplatformProviderRecordDO);
			Long openplatformProviderRecordDOId = openplatformProviderRecordDO.getId();

			// 参数保存
			OpenplatformOpenapiRecordDomainEventContentRecordParam providerRecordParam = providerRecord.getRecordParam();
			if (providerRecordParam != null) {
				OpenplatformProviderRecordParamDO openplatformProviderRecordParamDO = new OpenplatformProviderRecordParamDO();
				openplatformProviderRecordParamDO.setOpenplatformProviderRecordId(openplatformProviderRecordDOId);
				openplatformProviderRecordParamDO.setRequestParam(providerRecordParam.getRequestParam());
				openplatformProviderRecordParamDO.setResponseResult(providerRecordParam.getResponseResult());
				iOpenplatformProviderRecordParamService.save(openplatformProviderRecordParamDO);
			}
		}
		iOpenplatformOpenapiRecordService.updateIsExistProviderRecordToTrue(openplatformOpenapiRecordDOId);
	}

	/**
	 * 供应商调用记录映射实体
	 * @param providerRecord
	 * @param openplatformOpenapiRecordDOId
	 * @param ownerCustomerId
	 * @return
	 */
	private OpenplatformProviderRecordDO mappingOpenplatformProviderRecordDO(OpenplatformOpenapiRecordDomainEventContentProviderRecord providerRecord,
																			 Long openplatformOpenapiRecordDOId,Long ownerCustomerId) {
		OpenplatformProviderRecordDO openplatformProviderRecordDO = new OpenplatformProviderRecordDO();

		openplatformProviderRecordDO.setOpenplatformOpenapiRecordId(providerRecord.getOpenapiRecordId() != null ? providerRecord.getOpenapiRecordId() : openplatformOpenapiRecordDOId);
		openplatformProviderRecordDO.setCustomerId(providerRecord.getCustomerId() != null ? providerRecord.getCustomerId() : ownerCustomerId);
		openplatformProviderRecordDO.setRequestName(providerRecord.getRequestName());
		openplatformProviderRecordDO.setRequestUrl(providerRecord.getRequestUrl());
		openplatformProviderRecordDO.setRequestParameterMd5(providerRecord.getRequestParameterMd5());

		openplatformProviderRecordDO.setRequestAt(providerRecord.getRequestStartAt());

		openplatformProviderRecordDO.setResponseResultMd5(providerRecord.getResponseResultMd5());
		openplatformProviderRecordDO.setTraceId(providerRecord.getTraceId());
		openplatformProviderRecordDO.setHandleDuration(providerRecord.getHandleDuration());
		openplatformProviderRecordDO.setIsResponseHasEffectiveValue(providerRecord.getIsResponseHasEffectiveValue());
		openplatformProviderRecordDO.setResponseHttpStatus(providerRecord.getResponseHttpStatus());
		openplatformProviderRecordDO.setResponseBusinessStatus(providerRecord.getResponseBusinessStatus());
		// todo 供应商调用记录计费待完善
		openplatformProviderRecordDO.setFeeAmount(0);
		openplatformProviderRecordDO.setFeeReasonDictId(openFlatformFeeReasonDictId(OpenFlatformFeeReason.other_not_support));

		// 供应用商信息
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
		openplatformProviderRecordDO.setIsCacheHit(Optional.ofNullable(providerRecord.getIsCacheHit()).orElse(false));
		openplatformProviderRecordDO.setRemark(providerRecord.getRemark());

		return openplatformProviderRecordDO;
	}

	/**
	 * 获取原因
	 * @param openFlatformFeeReason
	 * @return
	 */
	private Long openFlatformFeeReasonDictId(OpenFlatformFeeReason openFlatformFeeReason) {
		return openFlatformFeeReasonDictIdCache.get(openFlatformFeeReason, () -> {
			return openplatformDictGateway.getDictIdByGroupCodeAndItemValue(openFlatformFeeReason.groupCode(), openFlatformFeeReason.itemValue());
		});
	}

}
