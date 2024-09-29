package com.particle.openplatform.infrastructure.bill.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 开放平台供应商接口月汇总表
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_provider_record_prd_api_month_summary")
public class OpenplatformProviderRecordPrdApiMonthSummaryDO extends BaseDO {

    /**
    * 供应商id
    */
    private Long openplatformProviderId;

    /**
    * 供应商接口id
    */
    private Long openplatformProviderApiId;

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
    * 平均单价金额，单位分
    */
    private Integer averageUnitPriceAmount;

    /**
    * 总消费金额，单位分
    */
    private Integer totalFeeAmount;

    /**
    * 描述
    */
    private String remark;


}
