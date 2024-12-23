package com.particle.usagecount.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 使用次数记录明细 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Data
@Schema
public class UsageCountRecordDetailVO extends AbstractBaseIdVO {

    @Schema(description = "使用次数记录id")
    private Long usageCountRecordId;

    @TransBy(tableName = TransTableNameConstants.component_usage_count_record, byFieldName = "usageCountRecordId", mapValueField = "usageCountKey")
    @Schema(description = "使用次数key")
    private String usageCountRecordUsageCountKey;

    @Schema(description = "使用次数定义id")
    private Long usageCountDefineId;

    @TransBy(tableName = TransTableNameConstants.component_usage_count_define, byFieldName = "usageCountDefineId", mapValueField = "name")
    @Schema(description = "使用次数定义名称")
    private String usageCountDefineName;

    @Schema(description = "使用用户id")
    private Long usageUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "usageUserId",mapValueField = "nickname")
    @Schema(description = "使用用户昵称")
    private String usageUserNickname;

    @Schema(description = "使用租户id")
    private Long usageTenantId;

    @TransBy(type = TransConstants.TRANS_TENANT_BY_ID,byFieldName = "usageTenantId",mapValueField = "name")
    @Schema(description = "使用租户名称")
    private String usageTenantName;

    @Schema(description = "创建时间，创建时间不等于请求时间，可能稍有延迟")
    private LocalDateTime createAt;

}
