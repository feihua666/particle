package com.particle.crawler.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 爬虫执行实例 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Data
@Schema
public class CrawlerExecutionUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "爬虫定义ID 不能为空")
        @Schema(description = "爬虫定义ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDefinitionId;


    @NotNull(message = "执行时使用的版本ID 不能为空")
        @Schema(description = "执行时使用的版本ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDefinitionHistoryId;


    @NotNull(message = "执行状态字典id：running/success/failed 不能为空")
        @Schema(description = "执行状态字典id：running/success/failed",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;


    @NotNull(message = "触发方式字典id：manual/api/schedule 不能为空")
        @Schema(description = "触发方式字典id：manual/api/schedule",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long triggerTypeDictId;


    @Schema(description = "全局上下文数据json")
    private String contextJson;


    @NotNull(message = "运行开始时间 不能为空")
        @Schema(description = "运行开始时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    

    @Schema(description = "错误信息")
    private String errorMsg;









}
