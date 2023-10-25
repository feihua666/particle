package com.particle.usagecount.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 使用次数记录明细 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Data
@Entity
public class UsageCountRecordDetail extends AggreateRoot {

    private UsageCountRecordDetailId id;

    /**
    * 使用次数记录id
    */
    private Long usageCountRecordId;

    /**
    * 使用次数定义id
    */
    private Long usageCountDefineId;

    /**
    * 使用用户id
    */
    private Long usageUserId;

    /**
    * 使用租户id
    */
    private Long usageTenantId;



    /**
     * 创建使用次数记录明细领域模型对象
     * @return 使用次数记录明细领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static UsageCountRecordDetail create(){
        return DomainFactory.create(UsageCountRecordDetail.class);
    }
}
