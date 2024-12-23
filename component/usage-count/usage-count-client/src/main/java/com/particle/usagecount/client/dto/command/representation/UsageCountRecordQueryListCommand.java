package com.particle.usagecount.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 使用次数记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Data
@Schema
public class UsageCountRecordQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "使用次数key")
    private String usageCountKey;


    @Schema(description = "使用次数定义id")
    private Long usageCountDefineId;


    @Schema(description = "使用次数配置id")
    private Long usageCountConfigId;


    @Schema(description = "已使用次数")
    private Integer usageCount;


    @Schema(description = "使用用户id")
    private Long usageUserId;


    @Schema(description = "使用租户id")
    private Long usageTenantId;









}
