package com.particle.agi.client.model.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * AI模型提供商 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Data
@Schema
public class AgiModelProviderUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "提供商编码 不能为空")
        @Schema(description = "提供商编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "提供商显示名称 不能为空")
        @Schema(description = "提供商显示名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "提供商类型 不能为空")
        @Schema(description = "提供商类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;


    @Schema(description = "API 基础地址")
    private String baseUrl;


    @Schema(description = "加密的 API Key")
    private String apiKeyEncrypted;


    @NotNull(message = "是否禁用 不能为空")
        @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @Schema(description = "扩展配置")
    private String extraConfigJson;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "描述")
    private String remark;









}
