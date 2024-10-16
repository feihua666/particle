package com.particle.openplatform.adapter.globalopenapi;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.openapi.api.limitrule.GlobalOpenapiRequestLimitService;
import com.particle.global.openapi.api.limitrule.IGlobalOpenapiRequestLimitDataProvider;
import com.particle.global.openapi.data.OpenapiAppQuotaLimitInfo;
import com.particle.global.openapi.enums.LimitRuleType;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 开放平台全局接口调用限制统计提供者实现
 * </p>
 *
 * @author yangwei
 * @since 2024/10/15 10:07
 */
@Component
public class GlobalOpenapiRequestLimitDataProviderImpl implements IGlobalOpenapiRequestLimitDataProvider {

    /**
     * 缓存5分钟
     */
    private TimedCache<String, OpenplatformAppDO> openplatformAppTimedCache = CacheUtil.newTimedCache(5 * 60 * 1000);
    private TimedCache<String, OpenplatformOpenapiDO> OpenplatformOpenapiTimedCache = CacheUtil.newTimedCache(5 * 60 * 1000);


    private IOpenplatformAppService iOpenplatformAppService;
    private IOpenplatformOpenapiService iOpenplatformOpenapiService;
    private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
    private IOpenplatformAppQuotaService iOpenplatformAppQuotaService;
    private OpenplatformDictGateway openplatformDictGateway;

    @Override
    public GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic statistic(String clientId, LocalDateTime startAt, LocalDateTime endAt) {
        OpenplatformAppDO byAppId = getOpenplatformAppDOByClientId(clientId);

        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam dayRtSummaryOpenAppIdStatisticsParam = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam.create(byAppId.getId(), startAt.toLocalDate(), endAt.toLocalDate());
        List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO> dayRtSummaryOpenAppIdStatisticsVIEWDOS
                = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.openAppIdStatistics(dayRtSummaryOpenAppIdStatisticsParam);

        if (CollectionUtil.isNotEmpty(dayRtSummaryOpenAppIdStatisticsVIEWDOS)) {
            OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO statisticsVIEWDO = dayRtSummaryOpenAppIdStatisticsVIEWDOS.iterator().next();
            return GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic.createForValue(clientId,
                    statisticsVIEWDO.getTotalCall(), statisticsVIEWDO.getTotalFeeCall(), statisticsVIEWDO.getTotalFeeAmount());
        }
        return null;
    }

    @Override
    public GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic statistic(String clientId, String openapiCode, LocalDateTime startAt, LocalDateTime endAt) {
        OpenplatformAppDO byAppId = getOpenplatformAppDOByClientId(clientId);
        OpenplatformOpenapiDO byCode = getOpenplatformOpenapiDOByOpenapiCode(openapiCode);

        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam daySummaryOpenAppIdOpenapiIdStatisticsParam
                = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam.create(byAppId.getId(),byCode.getId(), startAt.toLocalDate(), endAt.toLocalDate());
        List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO> dayRtSummaryOpenAppIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.openAppIdOpenapiIdStatistics(daySummaryOpenAppIdOpenapiIdStatisticsParam);

        if (CollectionUtil.isNotEmpty(dayRtSummaryOpenAppIdStatisticsVIEWDOS)) {
            OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO statisticsVIEWDO = dayRtSummaryOpenAppIdStatisticsVIEWDOS.iterator().next();
            return GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic.createForValue(clientId,openapiCode,
                    statisticsVIEWDO.getTotalCall(), statisticsVIEWDO.getTotalFeeCall(), statisticsVIEWDO.getTotalFeeAmount());
        }
        return null;
    }

    @Override
    public OpenapiAppQuotaLimitInfo getOpenapiAppQuotaLimitInfo(String clientId) {
        OpenplatformAppDO byAppId = getOpenplatformAppDOByClientId(clientId);
        OpenplatformAppQuotaDO byOpenplatformAppId = iOpenplatformAppQuotaService.getByOpenplatformAppId(byAppId.getId());
        if (byOpenplatformAppId != null) {
            String limitTypeStr = openplatformDictGateway.getDictValueById(byOpenplatformAppId.getLimitTypeDictId());
            OpenapiAppQuotaLimitInfo openapiAppQuotaLimitInfo = OpenapiAppQuotaLimitInfo.createForValue(clientId,
                    LimitRuleType.valueOf(limitTypeStr),
                    byOpenplatformAppId.getLimitCount(), byOpenplatformAppId.getLimitFee());
            return openapiAppQuotaLimitInfo;
        }
        return null;
    }

    /**
     * 带缓存获取 OpenplatformAppDO
     * @param clientId
     * @return
     */
    private OpenplatformAppDO getOpenplatformAppDOByClientId(String clientId) {
        return openplatformAppTimedCache.get(clientId, false, () -> iOpenplatformAppService.getByAppId(clientId));
    }
    /**
     * 带缓存获取 OpenplatformOpenapiDO
     * @param openapiCode
     * @return
     */
    private OpenplatformOpenapiDO getOpenplatformOpenapiDOByOpenapiCode(String openapiCode) {
        return OpenplatformOpenapiTimedCache.get(openapiCode, false, () -> iOpenplatformOpenapiService.getByCode(openapiCode));
    }
    @Autowired
    public void setiOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
        this.iOpenplatformAppService = iOpenplatformAppService;
    }

    @Autowired
    public void setiOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
        this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
    }
    @Autowired
    public void setiOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService(IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService) {
        this.iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
    }
    @Autowired
    public void setiOpenplatformAppQuotaService(IOpenplatformAppQuotaService iOpenplatformAppQuotaService) {
        this.iOpenplatformAppQuotaService = iOpenplatformAppQuotaService;
    }
    @Autowired
    public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
        this.openplatformDictGateway = openplatformDictGateway;
    }
}
