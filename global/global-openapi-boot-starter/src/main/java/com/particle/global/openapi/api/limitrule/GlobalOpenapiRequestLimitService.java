package com.particle.global.openapi.api.limitrule;

import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.openapi.GlobalOpenapiAutoConfiguration;
import com.particle.global.openapi.data.OpenapiAppQuotaLimitInfo;
import com.particle.global.openapi.data.OpenapiLimitRuleInfo;
import com.particle.global.openapi.enums.LimitRulePeriod;
import com.particle.global.openapi.enums.LimitRuleTarget;
import com.particle.global.openapi.enums.LimitRuleType;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 请求限制
 * </p>
 *
 * @author yangwei
 * @since 2024/10/14 16:43
 */
@Slf4j
@Component
public class GlobalOpenapiRequestLimitService {

    @Qualifier(GlobalOpenapiAutoConfiguration.global_openapi_scheduled_task_executor)
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;
    @Autowired(required = false)
    private IGlobalOpenapiRequestLimitDataProvider globalOpenapiRequestLimitDataProvider;

    private static Map<String, RequestLimitClientIdStatistic> requestLimitClientIdStatisticMap = new ConcurrentHashMap<>();
    private static Map<String, RequestLimitClientIdAndOpenapiStatistic> requestLimitClientIdAndOpenapiStatisticMap = new ConcurrentHashMap<>();
    private static Map<String, OpenapiAppQuotaLimitInfo> requestAppQuotaLimitClientIdMap = new ConcurrentHashMap<>();

    /**
     * 请求限制
     * @param limitRuleInfo
     * @param clientId
     * @param apiCode
     * @param apiUrl
     */
    public void requestLimit(OpenapiLimitRuleInfo limitRuleInfo, String clientId, String apiCode, String apiUrl,String requestIp) {

        // ip白名单和黑名单检查
        ipsCheck(requestIp, limitRuleInfo.getWhiteIps(), limitRuleInfo.getBlackIps());

        LimitRuleType limitRuleType = limitRuleInfo.getLimitRuleType();
        if (limitRuleType == LimitRuleType.no_limit) {
            return;
        }
        LimitRuleTarget limitRuleTarget = limitRuleInfo.getLimitRuleTarget();
        LimitRulePeriod limitRulePeriod = limitRuleInfo.getLimitRulePeriod();
        Integer limitCount = limitRuleInfo.getLimitCount();
        Integer limitFee = limitRuleInfo.getLimitFee();
        String key = "";
        RequestLimitClientIdStatistic requestLimitClientIdStatistic = null;
        if (limitRuleTarget == LimitRuleTarget.client_id) {
            key += clientId + "_" + limitRulePeriod.name();
            requestLimitClientIdStatistic = requestLimitClientIdStatisticMap.computeIfAbsent(key, (k) -> RequestLimitClientIdStatistic.createForInit(clientId, limitRulePeriod));

        } else if (limitRuleTarget == LimitRuleTarget.client_id_and_openapi) {
            key += clientId + "_" + apiCode + "_"  + limitRulePeriod.name();
            requestLimitClientIdStatistic = requestLimitClientIdAndOpenapiStatisticMap.computeIfAbsent(key, (k) -> RequestLimitClientIdAndOpenapiStatistic.createForInit(clientId, apiCode, limitRulePeriod));
        }
        if (limitRuleType == LimitRuleType.count_limit) {
            // 0 不限制
            if (limitCount <= 0) {
                return;
            }
            Integer count = requestLimitClientIdStatistic.getCount();
            // 如果为null可能还没有获取到数据，不计算
            if (count == null) {
                return;
            }
            if (count > limitCount) {
                throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXCEED_REQUEST_COUNT);
            }
        }else if (limitRuleType == LimitRuleType.count_fee_limit) {
            // 0 不限制
            if (limitCount <= 0) {
                return;
            }
            Integer feeCount = requestLimitClientIdStatistic.getFeeCount();
            // 如果为null可能还没有获取到数据，不计算
            if (feeCount == null) {
                return;
            }
            if (feeCount > limitCount) {
                throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXCEED_REQUEST_FEE_COUNT);
            }
        } else if (limitRuleType == LimitRuleType.fee_limit) {
            // 0 不限制
            if (limitFee <= 0) {
                return;
            }
            Integer fee = requestLimitClientIdStatistic.getFee();
            // 如果为null可能还没有获取到数据，不计算
            if (fee == null) {
                return;
            }
            if (fee > limitFee) {
                throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXCEED_REQUEST_FEE);
            }
        }

    }

    /**
     * 请求ip查检，主要是ip白名单和黑名单
     * @param requestIp
     * @param whiteIps
     * @param blackIps
     */
    private void ipsCheck(String requestIp,String whiteIps,String blackIps) {
        if (StrUtil.isEmpty(requestIp)) {
            return;
        }
        if (StrUtil.isNotEmpty(blackIps)) {
            if (blackIps.contains(requestIp)) {
                throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.REQUEST_CLIENT_IN_IP_BLACK_LIST_ERROR);
            }
        }
        if (StrUtil.isNotEmpty(whiteIps)) {
            if (!whiteIps.contains(requestIp)) {
                throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.REQUEST_CLIENT_NOT_IN_IP_WHITE_LIST_ERROR);
            }
        }
    }
    /**
     * 请求应用额度限制
     */
    public void requestAppQuotaLimit( String clientId) {
        if (StrUtil.isEmpty(clientId)) {
            return;
        }
        OpenapiAppQuotaLimitInfo openapiAppQuotaLimitInfo = requestAppQuotaLimitClientIdMap.computeIfAbsent(clientId,k -> OpenapiAppQuotaLimitInfo.createForInit(clientId));
        if (openapiAppQuotaLimitInfo.getLimitRuleType() == null) {
            return;
        }
        if (openapiAppQuotaLimitInfo.hasExceedLimit()) {
            switch (openapiAppQuotaLimitInfo.getLimitRuleType()) {
                case count_limit:
                    throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXCEED_REQUEST_QUATA_COUNT);
                case count_fee_limit:
                    throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXCEED_REQUEST_QUATA_FEE_COUNT);
                case fee_limit:
                    throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXCEED_REQUEST_QUATA_FEE);

            }

        }
    }

    /**
     * 定时统计数据
     * 参见：{@link GlobalOpenapiRequestLimitOnApplicationRunnerListener}
     */
    public void sheduleStatisticData() {
        if (globalOpenapiRequestLimitDataProvider == null) {
            log.warn("sheduleStatisticData but not globalOpenapiRequestLimitStatisticProvider，ignored!");
            return;
        }
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.debug("sheduleStatisticData requestLimitClientIdStatistic start");
            long start = System.currentTimeMillis();
            requestLimitClientIdStatisticMap.forEach((k, v) -> {
                try {
                    LimitRulePeriod.LimitRulePeriodDateTime limitRulePeriodDateTime = v.limitRulePeriod.computeDateTime();
                    RequestLimitClientIdStatistic statistic = globalOpenapiRequestLimitDataProvider.statistic(v.clientId, limitRulePeriodDateTime.getStartAt(), limitRulePeriodDateTime.getEndAt());
                    v.updateValue(statistic);
                } catch (Exception e) {
                    log.error("sheduleStatisticData requestLimitClientIdStatistic error", e);
                }
            });

            log.debug("sheduleStatisticData requestLimitClientIdStatistic end,duration={}ms", System.currentTimeMillis() - start);
        }, 2, 2, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.debug("sheduleStatisticData requestLimitClientIdAndOpenapiStatistic start");
            long start = System.currentTimeMillis();
            requestLimitClientIdAndOpenapiStatisticMap.forEach((k, v) -> {
                try {
                    LimitRulePeriod.LimitRulePeriodDateTime limitRulePeriodDateTime = v.getLimitRulePeriod().computeDateTime();
                    ;
                    RequestLimitClientIdAndOpenapiStatistic statistic = globalOpenapiRequestLimitDataProvider.statistic(v.getClientId(),v.getOpenapiCode(), limitRulePeriodDateTime.getStartAt(), limitRulePeriodDateTime.getEndAt());
                    v.updateValue(statistic);
                } catch (Exception e) {
                    log.error("sheduleStatisticData requestLimitClientIdAndOpenapiStatistic error", e);
                }
            });
            log.debug("sheduleStatisticData requestLimitClientIdAndOpenapiStatistic end,duration={}ms", System.currentTimeMillis() - start);
        }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * 定时统计应用额度
     * 参见：{@link GlobalOpenapiRequestLimitOnApplicationRunnerListener}
     */
    public void scheduleAppQuotaLimitData() {

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.debug("scheduleAppQuotaLimitData start");
            long start = System.currentTimeMillis();
            requestAppQuotaLimitClientIdMap.forEach((k,v) -> {
                OpenapiAppQuotaLimitInfo openapiAppQuotaLimitInfo = globalOpenapiRequestLimitDataProvider.getOpenapiAppQuotaLimitInfo(k);
                v.updateValue(openapiAppQuotaLimitInfo);
            });
            log.debug("scheduleAppQuotaLimitData end,duration={}ms", System.currentTimeMillis() - start);

        }, 3, 2, TimeUnit.SECONDS);
    }
    /**
     * 客户端id的请求限制统计
     */
    @Data
    public static class RequestLimitClientIdStatistic {
        /**
         * 客户端id
         */
        private String clientId;
        /**
         * 调用次数
         */
        private Integer count;

        /**
         * 调用计费次数
         */
        private Integer feeCount;
        /**
         * 调用费用
         */
        private Integer fee;

        /**
         * 调用周期
         */
        private LimitRulePeriod limitRulePeriod;

        /**
         * 更新值,如果获取的统计数据为null，说明没有数据，或者手动删除了数据等原因
         * @param statistic
         */
        public void updateValue(RequestLimitClientIdStatistic statistic) {
            this.count = statistic == null ? null : statistic.count;
            this.feeCount = statistic == null ? null : statistic.feeCount;
            this.fee = statistic == null ? null : statistic.fee;
        }

        public static RequestLimitClientIdStatistic createForInit(String clientId,LimitRulePeriod limitRulePeriod) {
            RequestLimitClientIdStatistic requestLimitClientIdStatistic = new RequestLimitClientIdStatistic();
            requestLimitClientIdStatistic.clientId = clientId;
            requestLimitClientIdStatistic.limitRulePeriod = limitRulePeriod;
            return requestLimitClientIdStatistic;
        }

        public static RequestLimitClientIdStatistic createForValue(String clientId,
                                                           Integer count,
                                                           Integer feeCount,
                                                           Integer fee) {
            RequestLimitClientIdStatistic requestLimitClientIdStatistic = new RequestLimitClientIdStatistic();
            requestLimitClientIdStatistic.clientId = clientId;
            requestLimitClientIdStatistic.count = count;
            requestLimitClientIdStatistic.feeCount = feeCount;
            requestLimitClientIdStatistic.fee = fee;
            return requestLimitClientIdStatistic;
        }
    }

    /**
     * 客户端id和开放接口编码的请求限制统计
     */
    @Data
    public static class RequestLimitClientIdAndOpenapiStatistic extends RequestLimitClientIdStatistic {
        /**
         * 开放接口编码
         */
        private String openapiCode;

        public static RequestLimitClientIdAndOpenapiStatistic createForInit(String clientId, String openapiCode, LimitRulePeriod limitRulePeriod) {
            RequestLimitClientIdAndOpenapiStatistic requestLimitClientIdAndOpenapiStatistic = new RequestLimitClientIdAndOpenapiStatistic();
            requestLimitClientIdAndOpenapiStatistic.setClientId(clientId);
            requestLimitClientIdAndOpenapiStatistic.openapiCode = openapiCode;
            requestLimitClientIdAndOpenapiStatistic.setLimitRulePeriod(limitRulePeriod);
            return requestLimitClientIdAndOpenapiStatistic;
        }
        public static RequestLimitClientIdAndOpenapiStatistic createForValue(String clientId, String openapiCode,
                                                                     Integer count,
                                                                     Integer feeCount,
                                                                     Integer fee) {
            RequestLimitClientIdAndOpenapiStatistic requestLimitClientIdAndOpenapiStatistic = new RequestLimitClientIdAndOpenapiStatistic();
            requestLimitClientIdAndOpenapiStatistic.setClientId(clientId);
            requestLimitClientIdAndOpenapiStatistic.setCount(count);
            requestLimitClientIdAndOpenapiStatistic.setFeeCount(feeCount);
            requestLimitClientIdAndOpenapiStatistic.setFee(fee);
            requestLimitClientIdAndOpenapiStatistic.openapiCode = openapiCode;
            return requestLimitClientIdAndOpenapiStatistic;
        }
    }
}
