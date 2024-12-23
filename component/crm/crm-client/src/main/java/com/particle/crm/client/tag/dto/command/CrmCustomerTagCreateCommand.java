package com.particle.crm.client.tag.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 客户标签 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Data
@Schema
public class CrmCustomerTagCreateCommand extends AbstractBaseCommand {



    @Schema(description = "标签编码")
    private String code;


    @NotEmpty(message = "标签名称 不能为空")
        @Schema(description = "标签名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "备注")
    private String remark;









}
