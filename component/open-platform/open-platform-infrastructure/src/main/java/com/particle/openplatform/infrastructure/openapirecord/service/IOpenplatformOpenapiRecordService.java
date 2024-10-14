package com.particle.openplatform.infrastructure.openapirecord.service;

import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.openapirecord.dos.view.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.openapirecord.dto.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口调用记录 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
public interface IOpenplatformOpenapiRecordService extends IBaseService<OpenplatformOpenapiRecordDO> {



    /**
     * 开放接口调用记录统计
     * @param openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam
     * @return
     */
    List<OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO> openAppIdOpenapiIdStatistics (OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam);


}
