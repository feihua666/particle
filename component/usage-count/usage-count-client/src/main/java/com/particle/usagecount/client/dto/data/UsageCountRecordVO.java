package com.particle.usagecount.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 使用次数记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Data
@Schema
public class UsageCountRecordVO extends AbstractBaseIdVO {

    @Schema(description = "使用次数key")
    private String usageCountKey;

    @Schema(description = "使用次数定义id")
    private Long usageCountDefineId;

    @TransBy(tableName = TransTableNameConstants.component_usage_count_define, byFieldName = "usageCountDefineId", mapValueField = "name")
    @Schema(description = "使用次数定义名称")
    private String usageCountDefineName;
    
    @Schema(description = "使用次数配置id")
    private Long usageCountConfigId;

    @TransBy(tableName = TransTableNameConstants.component_usage_count_config, byFieldName = "usageCountConfigId", mapValueField = "name")
    @Schema(description = "使用次数配置名称")
    private String usageCountConfigName;

    @Schema(description = "已使用次数")
    private Integer usageCount;

    @Schema(title = "使用用户id",description = "如果是按租户配置，将是第一次用户id，如果是按用户，将是用户的id")
    private Long usageUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "usageUserId",mapValueField = "nickname")
    @Schema(description = "使用用户昵称")
    private String usageUserNickname;

    @Schema(description = "使用租户id，非多租户系统没有值")
    private Long usageTenantId;

    @TransBy(type = TransConstants.TRANS_TENANT_BY_ID,byFieldName = "usageTenantId",mapValueField = "name")
    @Schema(description = "使用租户名称")
    private String usageTenantName;

}
