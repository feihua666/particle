package com.particle.openplatform.infrastructure.bill.mapper;

import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Mapper
public interface OpenplatformOpenapiRecordAppOpenapiDayRtSummaryMapper extends IBaseMapper<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> {
    /**
     * 开放平台应用开放接口日汇总统计
     * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam);

    /**
     * 开放平台应用日汇总统计
     * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO> openAppIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsParam);

}
