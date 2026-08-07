package com.particle.crawler.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 爬虫执行 根据爬虫定义直接执行指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-12 16:33:13
 */
@PropValid
@Data
@Schema
public class CrawlerExecutionExecuteCommand extends AbstractBaseCommand {

    @NotNull(message = "爬虫定义ID 不能为空")
    @Schema(description = "爬虫定义ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDefinitionId;

    @NotNull(message = "执行时使用的版本ID 不能为空")
    @Schema(description = "执行时使用的版本ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDefinitionHistoryId;

    @PropValid.DependCondition(message = "触发方式字典id 和 触发方式字典值 至少填写一个",dependProp = "triggerTypeDictValue",ifEqual = "empty")
    @Schema(description = "触发方式字典id")
    private Long triggerTypeDictId;

    @PropValid.DependCondition(message = "触发方式字典id 和 触发方式字典值 至少填写一个",dependProp = "triggerTypeDictId",ifEqual = "null")
    @Schema(description = "触发方式字典值")
    private String triggerTypeDictValue;

    /**
     * 参考对象 {@link com.particle.global.crawler.runtime.CrawlRuntimeOptions}
     */
    @Schema(description = "爬虫运行时选项 json")
    private String crawlRuntimeOptionsJson;

    /**
     * 需要根据执行定义规则取值逻辑传递对应的参数
     */
    @Schema(description = "执行参数 json")
    private Map<String,Object> param;
}
