package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Data
@Schema
public class OpenplatformOpenapiBatchQueryRecordPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;


    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;


    @Schema(description = "客户id")
    private Long customerId;


    @Schema(description = "执行状态")
    private Long executeStatusDictId;


    @Schema(description = "成功条数")
    private Integer successCount;


    @Schema(description = "失败条数")
    private Integer failCount;


    @Schema(description = "总条数")
    private Integer totalCount;


    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "查询时间")
    private LocalDateTime queryAt;


    @Schema(description = "追踪id")
    private String traceId;

	@Schema(description = "上传文件名")
	private String uploadFileName;

	@Schema(description = "导出的文件地址")
	private String exportFileUrl;

}
