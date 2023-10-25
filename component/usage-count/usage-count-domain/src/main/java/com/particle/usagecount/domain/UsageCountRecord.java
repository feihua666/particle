package com.particle.usagecount.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 使用次数记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Data
@Entity
public class UsageCountRecord extends AggreateRoot {

    private UsageCountRecordId id;

    /**
    * 使用次数key
    */
    private String usageCountKey;

    /**
    * 使用次数定义id
    */
    private Long usageCountDefineId;

    /**
    * 使用次数配置id
    */
    private Long usageCountConfigId;

    /**
    * 已使用次数
    */
    private Integer usageCount;

    /**
    * 使用用户id
    */
    private Long usageUserId;

    /**
    * 使用租户id
    */
    private Long usageTenantId;





    /**
     * 创建使用次数记录领域模型对象
     * @return 使用次数记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static UsageCountRecord create(){
        return DomainFactory.create(UsageCountRecord.class);
    }
}
