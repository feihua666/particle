package com.particle.openplatform.domain.bill;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
/**
 * <p>
 * 开放平台供应商接口日汇总 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Data
@Entity
public class OpenplatformProviderRecordPrdApiDaySummary extends AggreateRoot {

    private OpenplatformProviderRecordPrdApiDaySummaryId id;

    /**
    * 供应商id
    */
    private Long openplatformProviderId;

    /**
    * 供应商接口id
    */
    private Long openplatformProviderApiId;

    /**
    * 日期
    */
    private LocalDate dayAt;
    
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
     * 创建开放平台供应商接口日汇总领域模型对象
     * @return 开放平台供应商接口日汇总领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformProviderRecordPrdApiDaySummary create(){
        return DomainFactory.create(OpenplatformProviderRecordPrdApiDaySummary.class);
    }
}
