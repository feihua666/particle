package com.particle.crm.client.customer.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 客户联系方式 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Data
@Schema
public class CrmCustomerContactUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "客户id 不能为空")
        @Schema(description = "客户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crmCustomerId;


    @NotNull(message = "联系方式类型 不能为空")
    @Schema(description = "联系方式类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long contactTypeDictId;


    @NotEmpty(message = "联系方式 不能为空")
        @Schema(description = "联系方式",requiredMode = Schema.RequiredMode.REQUIRED)
    private String contact;


    @Schema(description = "备注")
    private String remark;









}
