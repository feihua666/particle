package com.particle.openplatform.domain.bill;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台应用开放接口月汇总 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Data
@Entity
public class OpenplatformOpenapiRecordAppOpenapiMonthSummary extends AggreateRoot {

    private OpenplatformOpenapiRecordAppOpenapiMonthSummaryId id;

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 应用id
    */
    private String appId;

    /**
    * 开放平台接口id
    */
    private Long openplatformOpenapiId;

    /**
    * 年
    */
    private Integer year;

    /**
    * 月，取值范围1-12
    */
    private Integer month;

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
    private Integer averageUnitPriceAmount;

    /**
    * 总消费金额，单位分
    */
    private Integer totalFeeAmount;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建开放平台应用开放接口月汇总领域模型对象
     * @return 开放平台应用开放接口月汇总领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapiRecordAppOpenapiMonthSummary create(){
        return DomainFactory.create(OpenplatformOpenapiRecordAppOpenapiMonthSummary.class);
    }
}
