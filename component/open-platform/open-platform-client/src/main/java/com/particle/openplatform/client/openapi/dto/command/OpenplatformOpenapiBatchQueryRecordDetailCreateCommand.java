package com.particle.openplatform.client.openapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 开放接口批量查询记录明细 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Data
@Schema
public class OpenplatformOpenapiBatchQueryRecordDetailCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "开放接口批量查询记录id 不能为空")
        @Schema(description = "开放接口批量查询记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformOpenapiBatchQueryRecordId;


    @NotNull(message = "执行状态 不能为空")
        @Schema(description = "执行状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long executeStatusDictId;


    @Schema(description = "是否成功")
    private Boolean isSuccess;

	@Schema(description = "请求流水号，查询后有值")
	private String requestNonce;



        @Schema(description = "查询时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime queryAt;
    


        @Schema(description = "追踪id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String traceId;



        @Schema(description = "追踪分支id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String spanId;


    @Schema(description = "请求参数")
    private String requestParam;


    @Schema(description = "请求查询字符串")
    private String queryString;


    @Schema(description = "响应结果")
    private String responseResult;









}