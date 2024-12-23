package com.particle.openplatform.client.openapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 开放接口批量查询记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Data
@Schema
public class OpenplatformOpenapiBatchQueryRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "开放平台应用id 不能为空")
        @Schema(description = "开放平台应用id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformAppId;


    @NotNull(message = "开放接口id 不能为空")
        @Schema(description = "开放接口id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformOpenapiId;


    @Schema(description = "客户id")
    private Long customerId;


    @NotNull(message = "执行状态 不能为空")
        @Schema(description = "执行状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long executeStatusDictId;


    @NotNull(message = "成功条数 不能为空")
        @Schema(description = "成功条数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer successCount;


    @NotNull(message = "失败条数 不能为空")
        @Schema(description = "失败条数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer failCount;


    @NotNull(message = "总条数 不能为空")
        @Schema(description = "总条数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalCount;


    @NotNull(message = "用户id 不能为空")
        @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;


    @NotNull(message = "查询时间 不能为空")
        @Schema(description = "查询时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime queryAt;


    @NotEmpty(message = "追踪id 不能为空")
        @Schema(description = "追踪id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String traceId;

	@Schema(description = "上传文件名")
	private String uploadFileName;

	@Schema(description = "导出的文件地址")
	private String exportFileUrl;


    @Schema(description = "描述")
    private String remark;









}
