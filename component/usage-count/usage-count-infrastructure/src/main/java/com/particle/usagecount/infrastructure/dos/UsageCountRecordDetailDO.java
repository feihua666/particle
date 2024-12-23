package com.particle.usagecount.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 使用次数记录明细表
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Data
@TableName("component_usage_count_record_detail")
public class UsageCountRecordDetailDO extends BaseDO {

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


}
