package com.particle.crawler.client.definition.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫项目 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Data
@Schema
public class CrawlerProjectPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like
    @Schema(description = "项目名称,左前缀匹配")
    private String name;


    @Schema(description = "归属用户id")
    private Long userId;


    @Schema(description = "是否公开")
    private Boolean isPublic;


}
