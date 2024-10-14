package com.particle.openplatform.infrastructure.bill.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口月汇总 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Mapper
public interface OpenplatformOpenapiRecordAppOpenapiMonthSummaryMapper extends IBaseMapper<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> {
    /**
     * 开放平台应用开放接口月汇总 应用统计
     * @param openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO> openAppIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam);

    /**
     * 开放平台应用开放接口月汇总 客户统计
     * @param openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO> customerIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam openapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam);

}
