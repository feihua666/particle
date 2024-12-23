package com.particle.crm.client.tag.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 客户标签关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Data
@Schema
public class CrmCustomerTagRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "客户id 不能为空")
        @Schema(description = "客户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crmCustomerId;


    @NotNull(message = "标签id 不能为空")
        @Schema(description = "标签id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crmCustomerTagId;









}
