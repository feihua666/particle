package com.particle.openplatform.infrastructure.bill.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * <p>
 * 开放平台应用开放接口日汇总表
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_openapi_record_app_openapi_day_summary")
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryDO extends BaseDO {

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * appId
    */
    private String appId;

    /**
    * 开放平台接口id
    */
    private Long openplatformOpenapiId;

    /**
    * 日期
    */
    private LocalDate dayAt;
    
    /**
    * 客户id
    */
    private Long customerId;

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
