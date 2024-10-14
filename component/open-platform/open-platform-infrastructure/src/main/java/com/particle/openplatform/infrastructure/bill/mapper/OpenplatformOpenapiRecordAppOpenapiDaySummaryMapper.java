package com.particle.openplatform.infrastructure.bill.mapper;

import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.openapirecord.dos.view.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.openapirecord.dto.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口日汇总 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Mapper
public interface OpenplatformOpenapiRecordAppOpenapiDaySummaryMapper extends IBaseMapper<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> {
    /**
     * 开放平台应用开放接口日汇总统计
     * @param openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam);

}
