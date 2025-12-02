package com.particle.data.client.dynamictable.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据表格上传记录 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Data
@Schema
public class DynamicTableUploadRecordCreateCommand extends AbstractBaseCommand {



    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;


    @NotEmpty(message = "上传文件名称 不能为空")
        @Schema(description = "上传文件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uploadFileName;


    @NotEmpty(message = "上传文件地址 不能为空")
        @Schema(description = "上传文件地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uploadFileUrl;


    @NotNull(message = "上传字段数量 不能为空")
        @Schema(description = "上传字段数量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer uploadTableFieldNum;


    @NotNull(message = "上传数据数量 不能为空")
        @Schema(description = "上传数据数量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer uploadTableDataNum;

	@Schema(description = "是否发布，1=是，0=否")
	private Boolean isPublic;









}