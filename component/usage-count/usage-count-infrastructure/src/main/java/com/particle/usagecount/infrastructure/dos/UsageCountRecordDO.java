package com.particle.usagecount.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 使用次数记录表
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Data
@TableName("component_usage_count_record")
public class UsageCountRecordDO extends BaseDO {

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


}
