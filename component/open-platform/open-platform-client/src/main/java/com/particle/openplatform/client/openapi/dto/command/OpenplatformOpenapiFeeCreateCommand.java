package com.particle.openplatform.client.openapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台开放接口费用 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Data
@Schema
public class OpenplatformOpenapiFeeCreateCommand extends AbstractBaseCommand {

	@Schema(description = "费用名称，可以理解为类似一个套餐")
	private String name;



    @NotNull(message = "单价 不能为空")
        @Schema(description = "单价",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer price;


    @NotNull(message = "计费方式 不能为空")
        @Schema(description = "计费方式",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long feeTypeDictId;


    @Schema(description = "按计费方式配置")
    private String feeTypeJson;


    @NotNull(message = "去重方式 不能为空")
        @Schema(description = "去重方式",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deduplicateTypeDictId;


    @Schema(description = "去重条数")
    private Integer deduplicateCount;


    @NotNull(message = "是否按请求参数去重 不能为空")
        @Schema(description = "是否按请求参数去重",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDeduplicateByParameter;


    @NotNull(message = "是否检查是否返回值 不能为空")
        @Schema(description = "是否检查是否返回值",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCheckHasValue;


    @NotNull(message = "是否检查处理时长 不能为空")
        @Schema(description = "是否检查处理时长",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCheckHandleDuration;


    @Schema(description = "处理时长")
    private Integer handleDuration;


    @Schema(description = "描述")
    private String remark;









}
