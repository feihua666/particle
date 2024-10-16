package com.particle.openplatform.domain.app;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台应用额度 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Data
@Entity
public class OpenplatformAppQuota extends AggreateRoot {

    private OpenplatformAppQuotaId id;

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 限制方式，字典id，如：按条限制，按金额费用限制，不限制有等
    */
    private Long limitTypeDictId;

    /**
    * 限制条数
    */
    private Integer limitCount;

    /**
    * 限制金额费用，单位分
    */
    private Integer limitFee;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建开放平台应用额度领域模型对象
     * @return 开放平台应用额度领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformAppQuota create(){
        return DomainFactory.create(OpenplatformAppQuota.class);
    }
}
