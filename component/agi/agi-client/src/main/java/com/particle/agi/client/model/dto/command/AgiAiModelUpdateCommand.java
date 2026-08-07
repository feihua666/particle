package com.particle.agi.client.model.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <p>
 * AI模型 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Data
@Schema
public class AgiAiModelUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "模型编码 不能为空")
        @Schema(description = "模型编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "模型显示名称 不能为空")
        @Schema(description = "模型显示名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "模型类型 不能为空")
        @Schema(description = "模型类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;


    @Schema(description = "最大 Token 数")
    private Integer maxTokens;


    @Schema(description = "默认温度值（0.00-2.00）")
    private BigDecimal defaultTemperature;
    

    @Schema(description = "默认 Top P 值")
    private BigDecimal defaultTopP;
    

    @NotNull(message = "是否禁用 不能为空")
        @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @NotNull(message = "是否为默认模型 不能为空")
        @Schema(description = "是否为默认模型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDefault;


    @Schema(description = "扩展配置")
    private String extraConfigJson;


    @NotNull(message = "所属提供商ID 不能为空")
        @Schema(description = "所属提供商ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long agiModelProviderId;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "描述")
    private String remark;









}
