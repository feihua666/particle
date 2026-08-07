package com.particle.crawler.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 爬虫项目 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Data
@Schema
public class CrawlerProjectCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "项目名称 不能为空")
        @Schema(description = "项目名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "配置参数json")
    private String configJson;


    @Schema(description = "描述")
    private String remark;









}
