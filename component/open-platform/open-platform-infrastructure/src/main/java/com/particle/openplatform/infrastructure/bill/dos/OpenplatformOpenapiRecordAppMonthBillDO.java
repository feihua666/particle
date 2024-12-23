package com.particle.openplatform.infrastructure.bill.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 开放平台应用月账单表
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_openapi_record_app_month_bill")
public class OpenplatformOpenapiRecordAppMonthBillDO extends BaseDO {

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

	/**
	 * appId
	 */
	private String appId;

	/**
	 * 客户id
	 */
	private Long customerId;

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

    /**
    * 账单状态，字典id，如：初始生成、已结清
    */
    private Long statusDictId;

    /**
    * 描述
    */
    private String remark;


}
