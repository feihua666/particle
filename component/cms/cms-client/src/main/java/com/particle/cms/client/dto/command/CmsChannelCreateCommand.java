package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * <p>
 * 栏目 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Schema
public class CmsChannelCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "站点id 不能为空")
        @Schema(description = "站点id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long cmsSiteId;


    @Schema(description = "栏目编码")
    private String code;


    @NotEmpty(message = "栏目名称 不能为空")
        @Schema(description = "栏目名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

	@Schema(description = "网页标题，用于页面标题")
	private String title;

	@Schema(description = "栏目访问上下文路径，主要应用于动态页访问，可以实现在一个站点下不同的栏目")
	private String channelContextPath;


        @Schema(description = "栏目模板路径")
    private String templatePath;


        @Schema(description = "栏目模板")
    private String templateIndex;


    @Schema(description = "栏目静态页存放路径")
    private String staticSavePath;

	@Schema(description = "简介")
	private String profile;

	@Schema(description = "是否发布")
	private Boolean isPublic;

	@Schema(description = "发布时间")
	private LocalDateTime publicAt;

	@Schema(description = "备注")
	private String remark;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "初始页面访问量,页面展示次数")
    private Integer initPv;


    @Schema(description = "关联的内容id,适用点击栏目访问内容详情的场景")
    private Long relatedCmsContentId;

	@Schema(description = "自定义url,适用于点击栏目访问自定义url的场景")
	private String customUrl;

    @Schema(description = "父级")
    private Long parentId;


}
