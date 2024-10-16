package com.particle.openplatform.adapter.globalopenapi;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.openapi.api.limitrule.GlobalOpenapiRequestLimitService;
import com.particle.global.openapi.api.limitrule.IGlobalOpenapiRequestLimitStatisticProvider;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO;
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
public class GlobalOpenapiRequestLimitStatisticProviderImpl implements IGlobalOpenapiRequestLimitStatisticProvider {

    private IOpenplatformAppService iOpenplatformAppService;
    private IOpenplatformOpenapiService iOpenplatformOpenapiService;
    private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;

    @Override
    public GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic statistic(String clientId, LocalDateTime startAt, LocalDateTime endAt) {
        OpenplatformAppDO byAppId = iOpenplatformAppService.getByAppId(clientId);
        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam.create(byAppId.getId(), startAt.toLocalDate(), endAt.toLocalDate());
        List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO> openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.openAppIdStatistics(openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam);
        if (CollectionUtil.isNotEmpty(openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDOS)) {
            OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO statisticsVIEWDO = openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDOS.iterator().next();
            return GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic.createForValue(clientId,
                    statisticsVIEWDO.getTotalCall(), statisticsVIEWDO.getTotalFeeCall(), statisticsVIEWDO.getTotalFeeAmount());
        }
        return null;
    }

    @Override
    public GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic statistic(String clientId, String openapiCode, LocalDateTime startAt, LocalDateTime endAt) {
        OpenplatformOpenapiDO byCode = iOpenplatformOpenapiService.getByCode(openapiCode);
        OpenplatformAppDO byAppId = iOpenplatformAppService.getByAppId(clientId);
        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam.create(byAppId.getId(), startAt.toLocalDate(), endAt.toLocalDate());
        List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO> openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.openAppIdStatistics(openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam);
        if (CollectionUtil.isNotEmpty(openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDOS)) {
            OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO statisticsVIEWDO = openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDOS.iterator().next();
            return GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic.createForValue(clientId,openapiCode,
                    statisticsVIEWDO.getTotalCall(), statisticsVIEWDO.getTotalFeeCall(), statisticsVIEWDO.getTotalFeeAmount());
        }
        return null;
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
}
