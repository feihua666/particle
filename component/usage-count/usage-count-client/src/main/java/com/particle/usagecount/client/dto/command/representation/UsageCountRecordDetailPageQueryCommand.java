package com.particle.usagecount.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 使用次数记录明细 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Data
@Schema
public class UsageCountRecordDetailPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "使用次数记录id")
    private Long usageCountRecordId;


    @Schema(description = "使用次数定义id")
    private Long usageCountDefineId;


    @Schema(description = "使用用户id")
    private Long usageUserId;


    @Schema(description = "使用租户id")
    private Long usageTenantId;









}
