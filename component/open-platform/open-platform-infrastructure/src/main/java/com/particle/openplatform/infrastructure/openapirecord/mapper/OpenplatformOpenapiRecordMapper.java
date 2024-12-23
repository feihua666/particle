package com.particle.openplatform.infrastructure.openapirecord.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.infrastructure.openapirecord.dos.view.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.openapirecord.dto.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口调用记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Mapper
public interface OpenplatformOpenapiRecordMapper extends IBaseMapper<OpenplatformOpenapiRecordDO> {

    /**
     * 开放接口调用记录统计
     * @param openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics (@Param("statisticsParams") OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam);

}
