package com.particle.usagecount.app.executor;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.WeakCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import com.particle.usagecount.domain.enums.UsageCountLimitPeriod;
import com.particle.usagecount.domain.enums.UsageCountLimitRuleType;
import com.particle.usagecount.domain.gateway.UsageCountDictGateway;
import com.particle.usagecount.domain.gateway.UsageCountRecordGateway;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import com.particle.usagecount.infrastructure.service.IUsageCountConfigService;
import com.particle.usagecount.infrastructure.service.IUsageCountDefineService;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordDetailService;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p>
 * 使用次数记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Slf4j
@Component
@Validated
public class UsageCountRecordMarkCommandExecutor extends AbstractBaseExecutor {

	private IUsageCountDefineService iUsageCountDefineService;
	private IUsageCountConfigService iUsageCountConfigService;

	private UsageCountDictGateway usageCountDictGateway;

	private IUsageCountRecordService iUsageCountRecordService;
	private IUsageCountRecordDetailService iUsageCountRecordDetailService;

	private ExecutorService commonDbTaskExecutor;

	private static WeakCache<String, UsageCountDefineDO> usageCountDefineByCodeCache = CacheUtil.newWeakCache(30 * 60 * 1000);
	private static WeakCache<String, UsageCountDefineDO> usageCountDefineByUrlPatternCache = CacheUtil.newWeakCache(31 * 60 * 1000);
	private static WeakCache<Long, List<UsageCountConfigDO>> usageCountConfigByUsageCountDefineIdCache = CacheUtil.newWeakCache(32 * 60 * 1000);


	/**
	 * key 为 {@link UsageCountLimitRuleType#itemValue()}
	 * value 为一个函数，参数分别为 当前登录用户用户id、当前登录租户id
	 * 返回值 为 一个字符串 用来拼接总体key的一部分
	 */
	private static Map<String, BiFunction<Long,Long,String>> limitRuleKeyMap = new HashMap<>(2);
	/**
	 * key 为 {@link UsageCountLimitPeriod#itemValue()}
	 * value 为一个函数
	 * 返回值 为 一个字符串 用来拼接总体key的一部分
	 */
	private static Map<String, Supplier<String>> limitPeriodKeyMap = new HashMap<>(5);

	static {
		String userCountValue = UsageCountLimitRuleType.user_count.itemValue();
		limitRuleKeyMap.put(userCountValue, (currentUserId,currentTenantId) -> {
			return userCountValue  + (currentTenantId == null ? "" : currentTenantId ) + currentUserId;
		});
		String tenantCountValue = UsageCountLimitRuleType.tenant_count.itemValue();

		limitRuleKeyMap.put(tenantCountValue, (currentUserId,currentTenantId) -> {
			return tenantCountValue + (currentTenantId == null ? "" : currentTenantId );
		});


		String dayPeriodValue = UsageCountLimitPeriod.day_period.itemValue();
		limitPeriodKeyMap.put(dayPeriodValue, () -> {
			return dayPeriodValue + DateUtil.today();
		});

		String weekPeriodValue = UsageCountLimitPeriod.week_period.itemValue();
		limitPeriodKeyMap.put(weekPeriodValue, () -> {
			return weekPeriodValue + DateUtil.weekOfYear(DateUtil.date());
		});

		String monthPeriodValue = UsageCountLimitPeriod.month_period.itemValue();
		limitPeriodKeyMap.put(monthPeriodValue, () -> {
			// 确保月从1开始，所以加1
			return monthPeriodValue + (DateUtil.month(DateUtil.date()) + 1);
		});

		String quarterPeriodValue = UsageCountLimitPeriod.quarter_period.itemValue();
		limitPeriodKeyMap.put(quarterPeriodValue, () -> {
			return quarterPeriodValue + (DateUtil.quarter(DateUtil.date()));
		});

		String yearPeriodValue = UsageCountLimitPeriod.year_period.itemValue();
		limitPeriodKeyMap.put(yearPeriodValue, () -> {
			return yearPeriodValue + (DateUtil.year(DateUtil.date()));
		});
	}

	/**
	 * 执行使用次数记录添加指令
	 * @param usageCountRecordMarkCommand
	 * @return
	 */
	public SingleResponse<UsageCountRecordMarkVO> execute(@Valid UsageCountRecordMarkCommand usageCountRecordMarkCommand) {

		UsageCountDefineDO usageCountDefineDO = null;
		String usageCountDefineCode = usageCountRecordMarkCommand.getUsageCountDefineCode();
		String urlPattern = usageCountRecordMarkCommand.getUrlPattern();
		Long currentUserId = usageCountRecordMarkCommand.getCurrentUserId();
		Long currentTenantId = usageCountRecordMarkCommand.getCurrentTenantId();

		if (StrUtil.isNotEmpty(usageCountDefineCode)) {
			usageCountDefineDO = usageCountDefineByCodeCache.get(usageCountDefineCode,() -> iUsageCountDefineService.getByCode(usageCountDefineCode));
		}else if (StrUtil.isNotEmpty(urlPattern)) {
			usageCountDefineDO = usageCountDefineByUrlPatternCache.get(urlPattern,() -> iUsageCountDefineService.getByUrlPattern(urlPattern));
		}

		if (usageCountDefineDO == null) {
			String format = StrUtil.format("usageCount mark cannot find any config by code={} or urlPattern={}", usageCountDefineCode, urlPattern);
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.CONFIG_ERROR, format);
		}

		Long usageCountDefineDOId = usageCountDefineDO.getId();
		List<UsageCountConfigDO> usageCountConfigDOS = usageCountConfigByUsageCountDefineIdCache.get(usageCountDefineDOId,() -> iUsageCountConfigService.getByUsageCountDefineId(usageCountDefineDOId));
		if (CollectionUtil.isEmpty(usageCountConfigDOS)) {
			String format = StrUtil.format("usageCount mark cannot find any config by code={} or urlPattern={}", usageCountDefineCode, urlPattern);
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.CONFIG_ERROR, format);
		}

		UsageCountConfigDO usageCountConfigDO = null;
		if (currentTenantId == null) {
			// 如果租户未设置，使用全局未设置租户的配置
			usageCountConfigDO = usageCountConfigDOS.stream().filter(item -> Objects.isNull(item.getLimitTenantId())).findFirst().orElse(null);
		}else {
			// 如果租户已设置尝试使用匹配的租户
			usageCountConfigDO = usageCountConfigDOS.stream().filter(item -> Objects.equals(item.getLimitTenantId(),currentTenantId)).findFirst().orElse(null);
			// 如果租户未匹配到对应的租户id，则使用全局未设置租户的配置
			if (usageCountConfigDO == null) {
				usageCountConfigDO = usageCountConfigDOS.stream().filter(item -> Objects.isNull(item.getLimitTenantId())).findFirst().orElse(null);
			}
		}
		// 未找到匹配的配置，直接返回失败
		if (usageCountConfigDO == null) {
			String format = StrUtil.format("usageCount mark cannot find config by code={} or urlPattern={}", usageCountDefineCode, urlPattern);
			log.warn(format);
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.CONFIG_ERROR, format);
		}

		String computeKey = computeKey(usageCountConfigDO, currentUserId, currentTenantId);
		UsageCountRecordDO usageCountRecordDO = iUsageCountRecordService.getByUsageCountKey(computeKey);
		// 由于添加和更新的差异，定义一个真正的计数变量，以设置真正的计数
		int realCount = 0;
		// 不存在添加
		if (usageCountRecordDO == null) {
			UsageCountRecordDO usageCountRecordDOToBeInsert = new UsageCountRecordDO();
			usageCountRecordDOToBeInsert.setUsageCountKey(computeKey);
			usageCountRecordDOToBeInsert.setUsageCount(1);
			usageCountRecordDOToBeInsert.setUsageCountConfigId(usageCountConfigDO.getId());
			usageCountRecordDOToBeInsert.setUsageCountDefineId(usageCountDefineDOId);
			// 用户应该理解为第一次开始计数的用户id
			usageCountRecordDOToBeInsert.setUsageUserId(currentUserId);
			usageCountRecordDOToBeInsert.setUsageTenantId(currentTenantId);
			// 在并发情况下，添加的时候有可能已存在
			try {
				usageCountRecordDO = iUsageCountRecordService.add(usageCountRecordDOToBeInsert);
				realCount = 1;
			} catch (Exception e) {
				// 尝试直接加1
				log.error("attempt add usageCountRecordDO computeKey = {} error",computeKey,e);
				iUsageCountRecordService.plusForColumnByColumn(computeKey, UsageCountRecordDO::getUsageCountKey, UsageCountRecordDO::getUsageCount, 1);
			}
		}else {
			iUsageCountRecordService.plusForColumnByColumn(computeKey, UsageCountRecordDO::getUsageCountKey, UsageCountRecordDO::getUsageCount, 1);
		}

		// 只有在添加失败时才有可能是null
		if (usageCountRecordDO == null) {
			usageCountRecordDO = iUsageCountRecordService.getByUsageCountKey(computeKey);
			realCount = usageCountRecordDO.getUsageCount();
		}else {
			// 判断一下是否为0，如果为0代表没有处理过该值，这里直接加1，否则可能是在添加了一个默认1导致不正确
			if (realCount <= 0) {
				realCount = usageCountRecordDO.getUsageCount() + 1;
			}
		}

		UsageCountRecordDO finalByUsageCountKey = usageCountRecordDO;
		commonDbTaskExecutor.execute(()->{
			// 记录明细
			UsageCountRecordDetailDO usageCountRecordDetailDO = new UsageCountRecordDetailDO();
			usageCountRecordDetailDO.setUsageCountRecordId(finalByUsageCountKey.getId());
			usageCountRecordDetailDO.setUsageCountDefineId(usageCountDefineDOId);
			usageCountRecordDetailDO.setUsageUserId(currentUserId);
			usageCountRecordDetailDO.setUsageTenantId(currentTenantId);
			iUsageCountRecordDetailService.add(usageCountRecordDetailDO);
		});

		UsageCountRecordMarkVO usageCountRecordMarkVO = UsageCountRecordMarkVO.create(usageCountRecordDO.getId(),
				computeKey,
				realCount,
				usageCountConfigDO.getLimitCount(),
				usageCountConfigDO.getExceedTip(),
				usageCountRecordDO.getVersion());
		return SingleResponse.of(usageCountRecordMarkVO);

	}


	/**
	 * 计算key值
	 * @param usageCountConfigDO
	 * @return
	 */
	private String computeKey(UsageCountConfigDO usageCountConfigDO,Long currentUserId,Long currentTenantId) {
		String limitRuleValue = usageCountDictGateway.getDictValueById(usageCountConfigDO.getLimitRuleTypeDictId());
		String limitPeriodValue = usageCountDictGateway.getDictValueById(usageCountConfigDO.getLimitPeriodDictId());

		List<String> keyList = new ArrayList<>();
		keyList.add(usageCountConfigDO.getId().toString());

		String limitRuleKey = limitRuleKeyMap.get(limitRuleValue).apply(currentUserId, currentTenantId);
		keyList.add(limitRuleKey);

		String limitPeriodKey = limitPeriodKeyMap.get(limitPeriodValue).get();
		keyList.add(limitPeriodKey);

		return keyList.stream().collect(Collectors.joining("__"));
	}

	@Autowired
	public void setiUsageCountDefineService(IUsageCountDefineService iUsageCountDefineService) {
		this.iUsageCountDefineService = iUsageCountDefineService;
	}

	@Autowired
	public void setiUsageCountConfigService(IUsageCountConfigService iUsageCountConfigService) {
		this.iUsageCountConfigService = iUsageCountConfigService;
	}

	@Autowired
	public void setUsageCountDictGateway(UsageCountDictGateway usageCountDictGateway) {
		this.usageCountDictGateway = usageCountDictGateway;
	}

	@Autowired
	public void setiUsageCountRecordService(IUsageCountRecordService iUsageCountRecordService) {
		this.iUsageCountRecordService = iUsageCountRecordService;
	}

	@Autowired
	public void setiUsageCountRecordDetailService(IUsageCountRecordDetailService iUsageCountRecordDetailService) {
		this.iUsageCountRecordDetailService = iUsageCountRecordDetailService;
	}

	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	public void setCommonDbTaskExecutor(ExecutorService commonDbTaskExecutor) {
		this.commonDbTaskExecutor = commonDbTaskExecutor;
	}
}
