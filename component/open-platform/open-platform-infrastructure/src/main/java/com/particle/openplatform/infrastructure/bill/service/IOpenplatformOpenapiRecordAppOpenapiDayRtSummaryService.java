package com.particle.openplatform.infrastructure.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
public interface IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService extends IBaseService<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> {

    /**
     * 获取某一天的数据
     * @param openplatformAppId
     * @param openplatformOpenapiId
     * @param dayAt
     * @return
     */
    default public OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO getByOpenplatformAppIdAndOpenplatformOpenapiIdAndDayAt(Long openplatformAppId, Long openplatformOpenapiId, LocalDate dayAt){
        LambdaQueryWrapper<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> lambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO>lambdaQuery()
                .eq(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO::getOpenplatformAppId, openplatformAppId)
                .eq(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO::getOpenplatformOpenapiId, openplatformOpenapiId)
                .eq(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO::getDayAt, dayAt);
        return getOne(lambdaQueryWrapper);
    }

    /**
     * 开放平台应用开放接口日汇总统计
     * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics (OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam);

    /**
     * 开放平台应用日汇总统计
     * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO> openAppIdStatistics (OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam);















}
