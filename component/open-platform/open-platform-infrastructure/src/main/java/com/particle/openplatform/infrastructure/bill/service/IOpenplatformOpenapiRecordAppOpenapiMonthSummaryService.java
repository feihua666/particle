package com.particle.openplatform.infrastructure.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam;

import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口月汇总 服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
public interface IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService extends IBaseService<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> {
    /**
     * 获取某一月的数据
     * @param openplatformAppId
     * @param openplatformOpenapiId
     * @param year
     * @param month
     * @return
     */
    default public OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO getByOpenplatformAppIdAndOpenplatformOpenapiIdAndYearAndMonth(Long openplatformAppId, Long openplatformOpenapiId, Integer year,Integer month){
        LambdaQueryWrapper<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> lambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO>lambdaQuery()
                .eq(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO::getOpenplatformAppId, openplatformAppId)
                .eq(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO::getOpenplatformOpenapiId, openplatformOpenapiId)
                .eq(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO::getYear, year)
                .eq(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO::getMonth, month);
        return getOne(lambdaQueryWrapper);
    }

    /**
     * 开放平台应用开放接口月汇总 应用统计
     * @param openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO> openAppIdStatistics (OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam);

    /**
     * 开放平台应用开放接口月汇总 客户统计
     * @param openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO> customerIdStatistics (OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam);


















}
