package com.particle.openplatform.infrastructure.bill.dos.view;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 开放平台应用开放接口月汇总 应用统计视图对象
 * 主要用于是汇总
 * </p>
 *
 * @author yangwei
 * @since 2024-10-11 16:37:32
 */
@Data
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO extends DTO {

    /**
     * 开放平台应用id
     */
    private Long openplatformAppId;

    /**
     * 年
     */
    private Integer year;

    /**
     * 月，取值范围1-12
     */
    private Integer month;
    /**
     * 调用总量
     */
    private Integer totalCall;

    /**
     * 调用计费总量
     */
    private Integer totalFeeCall;

    /**
     * 总消费金额，单位分
     */
    private Integer totalFeeAmount;


}
