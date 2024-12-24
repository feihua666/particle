package com.particle.openplatform.app.messaging;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.light.share.scheduler.SchedulerConstants;
import com.particle.global.messaging.event.messaging.MessageConsumer;
import com.particle.global.tool.json.JsonTool;
import com.particle.openplatform.domain.enums.OpenFlatformFeeReason;
import com.particle.openplatform.domain.enums.OpenPlatformDeduplicateType;
import com.particle.openplatform.domain.enums.OpenPlatformFeeType;
import com.particle.openplatform.domain.enums.OpenPlatformLimitRuleType;
import com.particle.openplatform.domain.event.*;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeValue;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
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

	/**
	 * 缓存8小时
	 */
	private static final TimedCache<OpenFlatformFeeReason, Long> openFlatformFeeReasonDictIdCache = CacheUtil.newTimedCache(1000 * 60 * 60 * 8);
	/**
	 * 缓存9小时
	 */
	private static final TimedCache<Long, OpenPlatformLimitRuleType> dictIdOpenPlatformLimitRuleTypeCache = CacheUtil.newTimedCache(1000 * 60 * 60 * 9);

	/**
	 * 日汇总
	 * key 为 openplatformAppId
	 * value 为扣除的费用
	 */
	private static final Map<AppOpenapiDayRtSummaryKey,AppOpenapiDayRtSummary> appOpenapiDayRtSummaryCache = new HashMap<>();
	private final Map<AppOpenapiDayRtSummaryKey, ReentrantLock> appOpenapiDayRtSummaryLockMap = new ConcurrentHashMap<>();

	/**
	 * 应用额度扣除
	 * key 为 openplatformAppId
	 * value 为扣除的费用
	 */
	private static final Map<Long,DeductAppQuota> deductAppQuotaCache = new HashMap<>();
	private final Map<Long, ReentrantLock> deductAppQuotaLockMap = new ConcurrentHashMap<>();

	private static LocalDate lastDeleteAppOpenapiDayRtSummaryLocalDate;

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

	@Autowired
	private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;

	@Autowired
	private IOpenplatformAppQuotaService iOpenplatformAppQuotaService;

	@Autowired
	private OpenplatformDictGateway openplatformDictGateway;

	@Qualifier(SchedulerConstants.default_global_scheduled_task_executor)
	@Autowired
	private ScheduledExecutorService scheduledExecutorService;

	@Value("${particle.openplatform.openapi.day-rt-summary.enable:true}")
	private Boolean isSaveAppOpenapiDayRtSummary;
	@Value("${particle.openplatform.openapi.deduct-app-quota.enable:true}")
	private Boolean isDeductAppQuota;

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
		// 计费信息配置
		OpenplatformOpenapiFeeValue openplatformOpenapiFee = contentRecord.getOpenplatformOpenapiFee();
		// 供应商信息
		List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords = data.getProviderRecords();
		// 标识是否存在供应商记录
		boolean hasProviderRecords = CollectionUtil.isNotEmpty(providerRecords);

		// 应用
		OpenplatformAppDO openplatformAppDO = iOpenplatformAppService.getByAppId(contentRecord.getAppId());
		Long ownerCustomerId = openplatformAppDO.getOwnerCustomerId();

		// 映射实体
		OpenplatformOpenapiRecordDO openplatformOpenapiRecordDO = mappingOpenplatformOpenapiRecordDO(contentRecord,
				hasProviderRecords, openplatformAppDO);

		// todo 在去重情况下，如果分布式部署有并发问题，可能计费不准确
		FeeResult feeResult = calculateOpenapiFee(openplatformOpenapiFee,
				openplatformOpenapiRecordDO.getIsResponseHasEffectiveValue(),
				openplatformOpenapiRecordDO.getHandleDuration(),
				openplatformOpenapiRecordDO.getOpenplatformOpenapiId(),
				openplatformOpenapiRecordDO.getRequestParameterMd5(),
				openplatformOpenapiRecordDO.getResponseHttpStatus());
		openplatformOpenapiRecordDO.setFeeAmount(feeResult.getFeeAmount());
		openplatformOpenapiRecordDO.setFeeReasonDictId(feeResult.getFeeReasonDictId());
		// 保存
		iOpenplatformOpenapiRecordService.save(openplatformOpenapiRecordDO);
		Long openplatformOpenapiRecordDOId = openplatformOpenapiRecordDO.getId();

		// 调用记录参数
		OpenplatformOpenapiRecordDomainEventContentRecordParam recordParam = contentRecord.getRecordParam();
		if (recordParam != null) {
			OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamDO = mappingOpenplatformOpenapiRecordParamDO(recordParam, openplatformOpenapiRecordDOId);
			iOpenplatformOpenapiRecordParamService.save(openplatformOpenapiRecordParamDO);
		}

		//	供应商调用记录
		if (hasProviderRecords) {
			saveProviderRecord(providerRecords, openplatformOpenapiRecordDOId, ownerCustomerId);
		}
		// 	实时日汇总
		if (isSaveAppOpenapiDayRtSummary != null && isSaveAppOpenapiDayRtSummary) {
			Long openplatformAppId = openplatformOpenapiRecordDO.getOpenplatformAppId();
			Long openplatformOpenapiId = openplatformOpenapiRecordDO.getOpenplatformOpenapiId();
			LocalDate dayAt = LocalDate.now();
			AppOpenapiDayRtSummaryKey appOpenapiDayRtSummaryKey = AppOpenapiDayRtSummaryKey.create(openplatformAppId, openplatformOpenapiId, dayAt);
			ReentrantLock reentrantLock = appOpenapiDayRtSummaryLockMap.computeIfAbsent(appOpenapiDayRtSummaryKey, k -> new ReentrantLock());
			reentrantLock.lock();
			AppOpenapiDayRtSummary appOpenapiDayRtSummary = appOpenapiDayRtSummaryCache.computeIfAbsent(appOpenapiDayRtSummaryKey,
					k -> AppOpenapiDayRtSummary.create(openplatformOpenapiRecordDO.getAppId(),openplatformOpenapiRecordDO.getCustomerId()));
			appOpenapiDayRtSummary.increment(openplatformOpenapiRecordDO.getFeeAmount());
			reentrantLock.unlock();
		}

		// 应用额度扣除,先缓存一部分，批量扣除
		if (isDeductAppQuota != null && isDeductAppQuota) {
			ReentrantLock reentrantLock = deductAppQuotaLockMap.computeIfAbsent(openplatformOpenapiRecordDO.getOpenplatformAppId(), k -> new ReentrantLock());
			reentrantLock.lock();
			DeductAppQuota deductAppQuota = deductAppQuotaCache.computeIfAbsent(openplatformOpenapiRecordDO.getOpenplatformAppId(), k -> DeductAppQuota.create());
			deductAppQuota.increment(openplatformOpenapiRecordDO.getFeeAmount());
			reentrantLock.unlock();
		}
	}

	/**
	 * 定时启动扣除应用额度
	 * 参见{@link com.particle.openplatform.messaging.OpenplatformOpenapiRecordMessageOnApplicationRunnerListener}
	 */
	public void scheduleDeductAppQuota() {
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			log.debug("openplatformOpenapiRecordMessage scheduleDeductAppQuota start");
			long start = System.currentTimeMillis();
			for (Map.Entry<Long, ReentrantLock> longReentrantLockEntry : deductAppQuotaLockMap.entrySet()) {
				longReentrantLockEntry.getValue().lock();
				Long openplatformAppId = longReentrantLockEntry.getKey();
				DeductAppQuota deductAppQuota = deductAppQuotaCache.get(openplatformAppId);
				deductAppQuotaCache.put(openplatformAppId, DeductAppQuota.create());
				longReentrantLockEntry.getValue().unlock();
				deductAppQuota(openplatformAppId, deductAppQuota, 10);
			}

			log.debug("openplatformOpenapiRecordMessage scheduleDeductAppQuota end,duration={}ms", System.currentTimeMillis() - start);
		}, 2, 2, TimeUnit.SECONDS);

	}
	/**
	 * 应用额度扣除
	 * @param openplatformAppId
	 * @param deductAppQuota 要扣除的金额
	 * @param reTryTimes
	 */
	private void deductAppQuota(Long openplatformAppId, DeductAppQuota deductAppQuota,int reTryTimes) {
		OpenplatformAppQuotaDO byOpenplatformAppId = iOpenplatformAppQuotaService.getByOpenplatformAppId(openplatformAppId);
		if (byOpenplatformAppId == null) {
			return;
		}
		Long limitTypeDictId = byOpenplatformAppId.getLimitTypeDictId();
		OpenPlatformLimitRuleType limitRuleType = dictIdOpenPlatformLimitRuleTypeCache.get(limitTypeDictId, () -> {
			String limitRuleTypeStr = openplatformDictGateway.getDictValueById(limitTypeDictId);
			return OpenPlatformLimitRuleType.valueOf(limitRuleTypeStr);
		});
		if (limitRuleType == OpenPlatformLimitRuleType.no_limit) {
			return;
		}
		Integer feeAmount = deductAppQuota.getFeeAmount();
		boolean needUpdate = false;
		if (limitRuleType == OpenPlatformLimitRuleType.count_limit) {
			needUpdate = true;
			byOpenplatformAppId.setLimitCount(byOpenplatformAppId.getLimitCount() - deductAppQuota.getCount());
		} else if (limitRuleType == OpenPlatformLimitRuleType.count_fee_limit) {
			if (feeAmount > 0) {
				needUpdate = true;
				byOpenplatformAppId.setLimitCount(byOpenplatformAppId.getLimitCount() - deductAppQuota.getFeeCount());
			}
		} else if (limitRuleType == OpenPlatformLimitRuleType.fee_limit) {
			needUpdate = true;
			byOpenplatformAppId.setLimitFee(byOpenplatformAppId.getLimitFee() - feeAmount);
		}
		if (needUpdate) {
			boolean save = iOpenplatformAppQuotaService.updateById(byOpenplatformAppId);
			if (!save) {
				if (reTryTimes <= 0) {
					log.error("deductAppQuota failed,openplatformAppId={},limitRuleType={},feeAmount={}",
							openplatformAppId,limitRuleType.name(), feeAmount);
					return;
				}
				// 更新失败，递归调用，可能数据已经被更新，再次调用，这里其实使用的是乐观锁
				deductAppQuota(openplatformAppId, deductAppQuota,reTryTimes - 1);
			}
		}
	}
	/**
	 * 删除一个月之前的实时日汇总
	 */
	private void deleteAppOpenapiDayRtSummary() {

		// 删除一个月之前的数据
		LocalDate localDate = LocalDate.now().withDayOfMonth(1);
		if (localDate.equals(lastDeleteAppOpenapiDayRtSummaryLocalDate)) {
			return;
		}
		lastDeleteAppOpenapiDayRtSummaryLocalDate = localDate;
		LambdaQueryWrapper<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> lambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO>lambdaQuery()
				.lt(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO::getDayAt, localDate);
		iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.remove(lambdaQueryWrapper);
	}
	/**
	 * 定时启动保存日汇总
	 * 参见{@link com.particle.openplatform.messaging.OpenplatformOpenapiRecordMessageOnApplicationRunnerListener}
	 */
	public void scheduleSaveAppOpenapiDayRtSummary() {
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			log.debug("openplatformOpenapiRecordMessage scheduleSaveAppOpenapiDayRtSummary start");
			long start = System.currentTimeMillis();
			// 删除一个月之前的实时日汇总
			deleteAppOpenapiDayRtSummary();

			for (Map.Entry<AppOpenapiDayRtSummaryKey, ReentrantLock> longReentrantLockEntry : appOpenapiDayRtSummaryLockMap.entrySet()) {
				longReentrantLockEntry.getValue().lock();
				AppOpenapiDayRtSummaryKey appOpenapiDayRtSummaryKey = longReentrantLockEntry.getKey();
				AppOpenapiDayRtSummary appOpenapiDayRtSummary = appOpenapiDayRtSummaryCache.get(appOpenapiDayRtSummaryKey);
				appOpenapiDayRtSummaryCache.put(appOpenapiDayRtSummaryKey, AppOpenapiDayRtSummary.create(appOpenapiDayRtSummary.getAppId(),appOpenapiDayRtSummary.getCustomerId()));
				longReentrantLockEntry.getValue().unlock();
				saveAppOpenapiDayRtSummary(appOpenapiDayRtSummaryKey, appOpenapiDayRtSummary);
			}

			log.debug("openplatformOpenapiRecordMessage scheduleSaveAppOpenapiDayRtSummary end,duration={}ms", System.currentTimeMillis() - start);
		}, 2, 7, TimeUnit.SECONDS);

	}
	/**
	 * 保存实时日汇总
	 * @param appOpenapiDayRtSummaryKey
	 * @param appOpenapiDayRtSummary
	 */
	private void saveAppOpenapiDayRtSummary(AppOpenapiDayRtSummaryKey appOpenapiDayRtSummaryKey,AppOpenapiDayRtSummary appOpenapiDayRtSummary) {
		Long openplatformAppId = appOpenapiDayRtSummaryKey.getOpenplatformAppId();
		Long openplatformOpenapiId = appOpenapiDayRtSummaryKey.getOpenplatformOpenapiId();
		LocalDate dayAt = appOpenapiDayRtSummaryKey.getDayAt();
		int reTryTimes = 10;
		Integer feeAmount = appOpenapiDayRtSummary.getFeeAmount();
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO dayRtSummaryDO = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.getByOpenplatformAppIdAndOpenplatformOpenapiIdAndDayAt(openplatformAppId, openplatformOpenapiId, dayAt);
		if (dayRtSummaryDO == null) {
			dayRtSummaryDO = new OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO();
			dayRtSummaryDO.setOpenplatformAppId(openplatformAppId);
			dayRtSummaryDO.setDayAt(dayAt);
			dayRtSummaryDO.setOpenplatformOpenapiId(openplatformOpenapiId);
			dayRtSummaryDO.setAppId(appOpenapiDayRtSummary.getAppId());
			dayRtSummaryDO.setCustomerId(appOpenapiDayRtSummary.getCustomerId());
			dayRtSummaryDO.setTotalCall(appOpenapiDayRtSummary.getCount());
			dayRtSummaryDO.setTotalFeeCall(appOpenapiDayRtSummary.getFeeCount());
			dayRtSummaryDO.setAverageUnitPriceAmount(appOpenapiDayRtSummary.computeAverageUnitPriceAmount());
			dayRtSummaryDO.setTotalFeeAmount(feeAmount);

			try {
				iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.save(dayRtSummaryDO);
			} catch (DuplicateKeyException e) {
				dayRtSummaryDO = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService
						.getByOpenplatformAppIdAndOpenplatformOpenapiIdAndDayAt(openplatformAppId, openplatformOpenapiId, dayAt);
				updateAppOpenapiDayRtSummary(dayRtSummaryDO.getId(),
						appOpenapiDayRtSummary, reTryTimes);
			}
		}else {
			updateAppOpenapiDayRtSummary(dayRtSummaryDO.getId(),
					appOpenapiDayRtSummary, reTryTimes);
		}
	}

	/**
	 * 使用乐观锁更新
	 * @param dayRtSummaryId
	 * @param appOpenapiDayRtSummary
	 * @param reTryTimes
	 */
	private void updateAppOpenapiDayRtSummary(Long dayRtSummaryId,AppOpenapiDayRtSummary appOpenapiDayRtSummary,int reTryTimes) {
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO dayRtSummaryDO = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.getById(dayRtSummaryId);
		if (dayRtSummaryDO == null) {
			return;
		}
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO dayRtSummaryDOToBeUpdate = new OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO();
		dayRtSummaryDOToBeUpdate.setId(dayRtSummaryDO.getId());
		dayRtSummaryDOToBeUpdate.setTotalCall(dayRtSummaryDO.getTotalCall() + 1);
		dayRtSummaryDOToBeUpdate.setTotalFeeCall(dayRtSummaryDO.getTotalFeeCall() + appOpenapiDayRtSummary.getFeeCount());
		dayRtSummaryDOToBeUpdate.setTotalFeeAmount(dayRtSummaryDO.getTotalFeeAmount() + appOpenapiDayRtSummary.getFeeAmount());
		/**
		 * 注意这里计算逻辑和{@link AppOpenapiDayRtSummary#computeAverageUnitPriceAmount()}保持一致
		 */
		dayRtSummaryDOToBeUpdate.setAverageUnitPriceAmount(new BigDecimal(dayRtSummaryDO.getTotalFeeAmount().doubleValue() / dayRtSummaryDO.getTotalCall()));
		dayRtSummaryDOToBeUpdate.setVersion(dayRtSummaryDO.getVersion());

		boolean save = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.updateById(dayRtSummaryDO);

		if (!save) {
			if (reTryTimes <= 0) {
				log.error("updateAppOpenapiDayRtSummary failed,openplatformOpenapiRecordAppOpenapiDayRtSummaryId={},feeAmount={}",
						dayRtSummaryId, appOpenapiDayRtSummary.getFeeAmount());
				return;
			}
			// 更新失败，递归调用，可能数据已经被更新，再次调用，这里其实使用的是乐观锁
			updateAppOpenapiDayRtSummary(dayRtSummaryId, appOpenapiDayRtSummary,reTryTimes - 1);
		}
	}
	/**
	 * 开放接口调用记录映射实体
	 * @param contentRecord
	 * @param hasProviderRecords
	 * @param openplatformAppDO
	 * @return
	 */
	private OpenplatformOpenapiRecordDO mappingOpenplatformOpenapiRecordDO(OpenplatformOpenapiRecordDomainEventContentRecord contentRecord,
																					 boolean hasProviderRecords,
																					 OpenplatformAppDO openplatformAppDO) {


		OpenplatformOpenapiRecordDO openplatformOpenapiRecordDO = new OpenplatformOpenapiRecordDO();

		openplatformOpenapiRecordDO.setOpenplatformAppId(openplatformAppDO.getId());
		openplatformOpenapiRecordDO.setOpenplatformOpenapiId(openplatformAppDO.getId());
		openplatformOpenapiRecordDO.setAppId(contentRecord.getAppId());
		openplatformOpenapiRecordDO.setUserId(contentRecord.getUserId());
		openplatformOpenapiRecordDO.setIsApp(contentRecord.getIsApp());
		Long ownerCustomerId = openplatformAppDO.getOwnerCustomerId();
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

		openplatformOpenapiRecordDO.setRequestHandleAt(contentRecord.getRequestStartAt());

		openplatformOpenapiRecordDO.setResponseResultMd5(contentRecord.getResponseResultMd5());
		openplatformOpenapiRecordDO.setTraceId(contentRecord.getTraceId());
		openplatformOpenapiRecordDO.setHandleDuration(contentRecord.getHandleDuration());
		openplatformOpenapiRecordDO.setIsResponseHasEffectiveValue(contentRecord.getIsResponseHasEffectiveValue());
		openplatformOpenapiRecordDO.setResponseHttpStatus(contentRecord.getResponseHttpStatus());
		openplatformOpenapiRecordDO.setResponseBusinessStatus(contentRecord.getResponseBusinessStatus());
		openplatformOpenapiRecordDO.setIsExistProviderRecord(hasProviderRecords);

		openplatformOpenapiRecordDO.setRemark(contentRecord.getRemark());

		return openplatformOpenapiRecordDO;
	}

	/**
	 * 开放接口调用记录参数映射实体
	 * @param recordParam
	 * @param openplatformOpenapiRecordDOId
	 * @return
	 */
	private OpenplatformOpenapiRecordParamDO mappingOpenplatformOpenapiRecordParamDO(OpenplatformOpenapiRecordDomainEventContentRecordParam recordParam,Long openplatformOpenapiRecordDOId) {
		OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamDO = new OpenplatformOpenapiRecordParamDO();
		openplatformOpenapiRecordParamDO.setOpenplatformOpenapiRecordId(openplatformOpenapiRecordDOId);
		openplatformOpenapiRecordParamDO.setRequestParam(recordParam.getRequestParam());
		openplatformOpenapiRecordParamDO.setResponseResult(recordParam.getResponseResult());

		return openplatformOpenapiRecordParamDO;
	}
	/**
	 * 保存供应商记录
	 * @param providerRecords
	 * @param openplatformOpenapiRecordDOId
	 * @param ownerCustomerId
	 */
	private void saveProviderRecord(List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords,
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

		openplatformProviderRecordDO.setOpenplatformOpenapiRecordId(openplatformOpenapiRecordDOId);
		openplatformProviderRecordDO.setCustomerId(ownerCustomerId);
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
	 * 计算调用费用
	 * @param apiFeeRuleInfo
	 * @param isResponseHasEffectiveValue
	 * @param handleDuration
	 * @param openplatformOpenapiId
	 * @param requestParameterMd5
	 * @param responseHttpStatus
	 * @return
	 */
	private FeeResult calculateOpenapiFee(OpenplatformOpenapiFeeValue apiFeeRuleInfo,
										  Boolean isResponseHasEffectiveValue,
										  Integer handleDuration,
										  Long openplatformOpenapiId,
										  String requestParameterMd5,Integer responseHttpStatus) {
		if (responseHttpStatus != null && responseHttpStatus > 400) {
			return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.response_http_status_error));
		}
		if (apiFeeRuleInfo == null) {
			// 未配置计费规则，不计费
			return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.not_exist_fee_rule));
		}

		boolean isCheckHasValue = apiFeeRuleInfo.getIsCheckHasValue() != null && apiFeeRuleInfo.getIsCheckHasValue();
		if (isCheckHasValue) {
			// 没有值，不计费
			if (!isResponseHasEffectiveValue) {
				return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.not_exist_effective_value));
			}
		}
		boolean isCheckHandleDuration = apiFeeRuleInfo.getIsCheckHandleDuration() != null && apiFeeRuleInfo.getIsCheckHandleDuration();
		if (isCheckHandleDuration) {
			// 响应超时，不计费
			if (handleDuration > apiFeeRuleInfo.getHandleDuration()) {
				return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.response_timeout));
			}
		}
		//  按次，每次接口调用需要计算计费
		if (apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_interface_call) {

			// 不去重，就是按单价计费
			if (OpenPlatformDeduplicateType.no_deduplicate == apiFeeRuleInfo.getOpenPlatformDeduplicateType()) {
				return FeeResult.create(apiFeeRuleInfo.getPrice(), openFlatformFeeReasonDictId(OpenFlatformFeeReason.per_price_no_deduplicate));
			}else if (OpenPlatformDeduplicateType.each_day_deduplicate == apiFeeRuleInfo.getOpenPlatformDeduplicateType()
			|| OpenPlatformDeduplicateType.each_week_deduplicate == apiFeeRuleInfo.getOpenPlatformDeduplicateType()
			|| OpenPlatformDeduplicateType.each_month_deduplicate == apiFeeRuleInfo.getOpenPlatformDeduplicateType()) {
				Page<OpenplatformOpenapiRecordDO> objectPage = new Page<>();
				Integer deduplicateCount = apiFeeRuleInfo.getDeduplicateCount();
				if (deduplicateCount < 2) {
					deduplicateCount = 2;
				}
				objectPage.setCurrent(1);
				objectPage.setSize(deduplicateCount);
				objectPage.setSearchCount(false);
				LocalDateTime localDateTime = beginAtOpenPlatformDeduplicateType(apiFeeRuleInfo.getOpenPlatformDeduplicateType());
				LambdaQueryWrapper<OpenplatformOpenapiRecordDO> openplatformOpenapiRecordDOLambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordDO>lambdaQuery()
						.select(OpenplatformOpenapiRecordDO::getFeeAmount)
						.eq(OpenplatformOpenapiRecordDO::getOpenplatformOpenapiId, openplatformOpenapiId)
						// 是否按参数去重
						.eq(apiFeeRuleInfo.getIsDeduplicateByParameter(), OpenplatformOpenapiRecordDO::getRequestParameterMd5, requestParameterMd5)
						.ge(OpenplatformOpenapiRecordDO::getRequestHandleAt, localDateTime)
						.orderByDesc(OpenplatformOpenapiRecordDO::getRequestHandleAt);
				Page<OpenplatformOpenapiRecordDO> page = iOpenplatformOpenapiRecordService.page(objectPage, openplatformOpenapiRecordDOLambdaQueryWrapper);
				List<OpenplatformOpenapiRecordDO> records = page.getRecords();
				if (CollectionUtil.isEmpty(records)) {
					// 首次计费
					return FeeResult.create(apiFeeRuleInfo.getPrice(), openFlatformFeeReasonDictId(OpenFlatformFeeReason.per_price_first));
				}else {
					long feeCount = records.stream().filter(o -> o.getFeeAmount() != null && o.getFeeAmount() > 0).count();
					if (feeCount > 0) {
						// 已存在计费，不计费
						return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.per_price_deduplicate));
					}else {
						// 达到去重周期计费
						return FeeResult.create(apiFeeRuleInfo.getPrice(), openFlatformFeeReasonDictId(OpenFlatformFeeReason.per_price_first_more));
					}
				}
			}
		}else if (apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_day
		|| apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_day
		|| apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_week
		|| apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_month
		|| apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_season
		|| apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.each_year) {
			// 	仅首次计费，后续不计费
			Page<OpenplatformOpenapiRecordDO> objectPage = new Page<>();
			objectPage.setCurrent(1);
			objectPage.setSize(1);
			objectPage.setSearchCount(false);
			LocalDateTime localDateTime = beginAtOpenOpenPlatformFeeType(apiFeeRuleInfo.getOpenPlatformFeeType());
			LambdaQueryWrapper<OpenplatformOpenapiRecordDO> openplatformOpenapiRecordDOLambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordDO>lambdaQuery()
					.select(OpenplatformOpenapiRecordDO::getId)
					.eq(OpenplatformOpenapiRecordDO::getOpenplatformOpenapiId, openplatformOpenapiId)
					.ge(OpenplatformOpenapiRecordDO::getRequestHandleAt, localDateTime)
					.gt(OpenplatformOpenapiRecordDO::getFeeAmount, 0)
					.orderByAsc(OpenplatformOpenapiRecordDO::getRequestHandleAt);
			Page<OpenplatformOpenapiRecordDO> page = iOpenplatformOpenapiRecordService.page(objectPage, openplatformOpenapiRecordDOLambdaQueryWrapper);
			List<OpenplatformOpenapiRecordDO> records = page.getRecords();
			if (CollectionUtil.isEmpty(records)) {
				// 首次计费
				return FeeResult.create(apiFeeRuleInfo.getPrice(), openFlatformFeeReasonDictId(OpenFlatformFeeReason.each_day__year_first));
			}else {
				// 已存在计费，不计费
				return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.each_day__year_deduplicate));
			}
		}else if (apiFeeRuleInfo.getOpenPlatformFeeType() == OpenPlatformFeeType.custom_config){
			// 	暂不支持的计费方式
		}

		// 其它不支持情况 不计费
		return FeeResult.create(0, openFlatformFeeReasonDictId(OpenFlatformFeeReason.other_not_support));
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
	/**
	 * 获取去重的开始时间
	 * @param openPlatformDeduplicateType
	 * @return
	 */
	private LocalDateTime beginAtOpenPlatformDeduplicateType(OpenPlatformDeduplicateType openPlatformDeduplicateType) {
		LocalDate today = LocalDate.now();
		if (OpenPlatformDeduplicateType.each_day_deduplicate == openPlatformDeduplicateType) {
			return today.atStartOfDay();
		}
		if (OpenPlatformDeduplicateType.each_week_deduplicate == openPlatformDeduplicateType) {
			// 获取当前周的开始日期（星期一）
			LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			return startOfWeek.atStartOfDay();
		}
		if (OpenPlatformDeduplicateType.each_month_deduplicate == openPlatformDeduplicateType) {
			// 获取当前月份的第一天
			LocalDate startOfMonth = today.withDayOfMonth(1);
			return startOfMonth.atStartOfDay();

		}
		return null;
	}
	/**
	 * 获取计费方式的开始时间
	 * @param openPlatformFeeType
	 * @return
	 */
	private LocalDateTime beginAtOpenOpenPlatformFeeType(OpenPlatformFeeType openPlatformFeeType) {
		LocalDate today = LocalDate.now();
		if (OpenPlatformFeeType.each_day == openPlatformFeeType) {
			return today.atStartOfDay();
		}
		if (OpenPlatformFeeType.each_week == openPlatformFeeType) {
			// 获取当前周的开始日期（星期一）
			LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			return startOfWeek.atStartOfDay();
		}
		if (OpenPlatformFeeType.each_month == openPlatformFeeType) {
			// 获取当前月份的第一天
			LocalDate startOfMonth = today.withDayOfMonth(1);
			return startOfMonth.atStartOfDay();

		}
		if (OpenPlatformFeeType.each_season == openPlatformFeeType) {
			// 获取当前季度的第一天
			LocalDate startOfSeason = getFirstDayOfQuarter(today);
			return startOfSeason.atStartOfDay();

		}
		if (OpenPlatformFeeType.each_year == openPlatformFeeType) {
			// 获取当前年的第一天
			LocalDate startOfYear = today.withDayOfYear(1);
			return startOfYear.atStartOfDay();

		}
		return null;
	}

	/**
	 * 季度的第一天
	 * @param date
	 * @return
	 */
	private LocalDate getFirstDayOfQuarter(LocalDate date) {
		// 获取月份，并计算它属于哪个季度
		int month = date.getMonthValue();
		Month quarterStartMonth;

		if (month >= 1 && month <= 3) {
			quarterStartMonth = Month.JANUARY;
		} else if (month >= 4 && month <= 6) {
			quarterStartMonth = Month.APRIL;
		} else if (month >= 7 && month <= 9) {
			quarterStartMonth = Month.JULY;
		} else { // month >= 10 && month <= 12
			quarterStartMonth = Month.OCTOBER;
		}

		// 返回该季度的第一天
		return LocalDate.of(date.getYear(), quarterStartMonth.getValue(), 1);
	}
	/**
	 * 计算费用结果类
	 */
	@Data
	private static class FeeResult{
		/**
		 * 消费金额，单位分
		 */
		private Integer feeAmount;

		/**
		 * 消费金额缘由，字典id
		 */
		private Long feeReasonDictId;

		/**
		 * 构建
		 * @param feeAmount
		 * @param feeReasonDictId
		 * @return
		 */
		public static FeeResult create(Integer feeAmount, Long feeReasonDictId) {
			FeeResult feeResult = new FeeResult();
			feeResult.feeAmount = feeAmount;
			feeResult.feeReasonDictId = feeReasonDictId;
			return feeResult;
		}
	}

	@Setter
	@Getter
	private static class AppOpenapiDayRtSummaryKey{
		private Long openplatformAppId;
		private Long openplatformOpenapiId;
		private LocalDate dayAt;

		public static AppOpenapiDayRtSummaryKey create(Long openplatformAppId, Long openplatformOpenapiId, LocalDate dayAt) {
			AppOpenapiDayRtSummaryKey appOpenapiDayRtSummaryKey = new AppOpenapiDayRtSummaryKey();
			appOpenapiDayRtSummaryKey.openplatformAppId = openplatformAppId;
			appOpenapiDayRtSummaryKey.openplatformOpenapiId = openplatformOpenapiId;
			appOpenapiDayRtSummaryKey.dayAt = dayAt;
			return appOpenapiDayRtSummaryKey;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			AppOpenapiDayRtSummaryKey that = (AppOpenapiDayRtSummaryKey) o;
			return Objects.equals(openplatformAppId, that.openplatformAppId) &&
					Objects.equals(openplatformOpenapiId, that.openplatformOpenapiId) &&
					Objects.equals(dayAt, that.dayAt);
		}

		@Override
		public int hashCode() {
			return Objects.hash(openplatformAppId, openplatformOpenapiId, dayAt);
		}
	}
	/**
	 * 应用额度扣减统计类
	 */
	@Data
	private static class DeductAppQuota{
		/**
		 * 消费金额，单位分
		 */
		private Integer feeAmount = 0;
		/**
		 * 调用次数
		 */
		private Integer count = 0;
		/**
		 * 消费次数
		 */
		private Integer feeCount = 0;
		public DeductAppQuota increment(Integer feeAmount) {
			this.feeAmount += feeAmount;
			this.count++;
			if (feeAmount > 0) {
				this.feeCount++;
			}
			return this;
		}
		public static DeductAppQuota create() {
			DeductAppQuota deductAppQuota = new DeductAppQuota();
			return deductAppQuota;
		}

	}
	/**
	 * 日汇总统计类
	 */
	@Data
	private static class AppOpenapiDayRtSummary{
		/**
		 * appId
		 */
		private String appId;
		/**
		 * 客户id
		 */
		private Long customerId;

		/**
		 * 消费金额，单位分
		 */
		private Integer feeAmount = 0;
		/**
		 * 调用次数
		 */
		private Integer count = 0;
		/**
		 * 消费次数
		 */
		private Integer feeCount = 0;

		public BigDecimal computeAverageUnitPriceAmount() {
            if (count == null) {
				return BigDecimal.ZERO;
            }
			return new BigDecimal(feeAmount.doubleValue() / count);
		}
		public AppOpenapiDayRtSummary increment(Integer feeAmount) {
			this.feeAmount += feeAmount;
			this.count++;
			if (feeAmount > 0) {
				this.feeCount++;
			}
			return this;
		}
		public static AppOpenapiDayRtSummary create(String appId, Long customerId) {
			AppOpenapiDayRtSummary appOpenapiDayRtSummary = new AppOpenapiDayRtSummary();
			return appOpenapiDayRtSummary;
		}

	}
}
