package com.particle.openplatform.domain.bill;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台应用月账单 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Data
@Entity
public class OpenplatformOpenapiRecordAppMonthBill extends AggreateRoot {

    private OpenplatformOpenapiRecordAppMonthBillId id;

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



    /**
     * 创建开放平台应用月账单领域模型对象
     * @return 开放平台应用月账单领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapiRecordAppMonthBill create(){
        return DomainFactory.create(OpenplatformOpenapiRecordAppMonthBill.class);
    }
}