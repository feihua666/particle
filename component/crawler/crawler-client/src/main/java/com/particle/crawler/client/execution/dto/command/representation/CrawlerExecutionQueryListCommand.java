package com.particle.crawler.client.execution.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 爬虫执行实例 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Data
@Schema
public class CrawlerExecutionQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "爬虫定义ID")
    private Long crawlerDefinitionId;


    @Schema(description = "执行时使用的版本ID")
    private Long crawlerDefinitionHistoryId;


    @Schema(description = "执行状态字典id：running/success/failed")
    private Long statusDictId;


    @Schema(description = "触发方式字典id：manual/api/schedule")
    private Long triggerTypeDictId;


    @Schema(description = "全局上下文数据json")
    private String contextJson;


    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    

    @Schema(description = "错误信息")
    private String errorMsg;









}
