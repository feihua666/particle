package com.particle.openplatform.infrastructure.bill.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
/**
 * <p>
 * 开放平台供应商月账单表
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_provider_record_prd_month_bill")
public class OpenplatformProviderRecordPrdMonthBillDO extends BaseDO {

    /**
    * 供应商id
    */
    private Long openplatformProviderId;

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
    private BigDecimal averageUnitPriceAmount;

    /**
    * 总消费金额，单位分
    */
    private Integer totalFeeAmount;

    /**
    * 描述
    */
    private String remark;


}
