package com.particle.data.client.dynamicdata.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据指标分类上传记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryUploadRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "动态数据指标分类id 不能为空")
        @Schema(description = "动态数据指标分类id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dynamicDataIndicatorCategoryId;


    @NotEmpty(message = "上传文件名称 不能为空")
        @Schema(description = "上传文件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uploadFileName;


    @NotEmpty(message = "上传文件地址 不能为空")
        @Schema(description = "上传文件地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uploadFileUrl;


    @NotNull(message = "上传指标数量 不能为空")
        @Schema(description = "上传指标数量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer uploadIndicatorNum;


    @NotNull(message = "上传数据数量 不能为空")
        @Schema(description = "上传数据数量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer uploadIndicatorDataNum;

	@Schema(description = "是否发布，1=是，0=否")
	private Boolean isPublic;









}