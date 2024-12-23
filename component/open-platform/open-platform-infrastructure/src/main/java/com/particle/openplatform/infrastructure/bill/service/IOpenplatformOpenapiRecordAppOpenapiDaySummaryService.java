package com.particle.openplatform.infrastructure.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口日汇总 服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
public interface IOpenplatformOpenapiRecordAppOpenapiDaySummaryService extends IBaseService<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> {


    /**
     * 获取某一天的数据
     * @param openplatformAppId
     * @param openplatformOpenapiId
     * @param dayAt
     * @return
     */
    default public OpenplatformOpenapiRecordAppOpenapiDaySummaryDO getByOpenplatformAppIdAndOpenplatformOpenapiIdAndDayAt(Long openplatformAppId, Long openplatformOpenapiId, LocalDate dayAt){
        LambdaQueryWrapper<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> lambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO>lambdaQuery()
                .eq(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO::getOpenplatformAppId, openplatformAppId)
                .eq(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO::getOpenplatformOpenapiId, openplatformOpenapiId)
                .eq(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO::getDayAt, dayAt);
        return getOne(lambdaQueryWrapper);
    }




    /**
     * 开放平台应用开放接口日汇总统计
     * @param openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics (OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam);











}
