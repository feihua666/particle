package com.particle.cms.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 模板 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@OrderBy("seq")
@Data
@Schema
public class CmsTemplateQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Schema(description = "唯一键")
    private String templateKey;


    @Schema(description = "文件名")
    private String name;


    @Schema(description = "是否为目录")
    private Boolean isDirectory;


    @Schema(description = "模板内容")
    private String content;


    @Schema(description = "排序")
    private Integer seq;





















}
