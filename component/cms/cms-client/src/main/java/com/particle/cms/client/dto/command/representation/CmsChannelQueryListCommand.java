package com.particle.cms.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 栏目 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Schema
public class CmsChannelQueryListCommand extends AbstractBaseTreeQueryCommand {

    @Schema(description = "栏目id")
    private Long id;

    @Schema(description = "站点id")
    private Long cmsSiteId;


    @Like
    @Schema(description = "栏目编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "栏目名称,左前缀匹配")
    private String name;

	@Schema(description = "栏目访问上下文路径，主要应用于动态页访问，可以实现在一个站点下不同的栏目")
	private String path;


    @Schema(description = "栏目模板路径")
    private String templatePath;


    @Schema(description = "栏目模板")
    private String templateIndex;


    @Schema(description = "栏目静态页存放路径")
    private String staticPath;


    @Schema(description = "页面访问量")
    private Integer pv;


    @Schema(description = "页面访问ip数")
    private Integer iv;


    @Schema(description = "页面访问用户数")
    private Integer uv;


    @Schema(description = "排序")
    private Integer seq;





















}
