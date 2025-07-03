package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Schema
public class CmsContentCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "站点id 不能为空")
        @Schema(description = "站点id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long cmsSiteId;


    @Schema(description = "栏目id")
    private Long cmsChannelId;


    @Schema(description = "内容分类id")
    private Long cmsContentCategoryId;


    @Schema(description = "标题")
    private String title;


    @NotEmpty(message = "作者 不能为空")
        @Schema(description = "作者",requiredMode = Schema.RequiredMode.REQUIRED)
    private String author;


    @Schema(description = "来源")
    private String original;


    @Schema(description = "简介")
    private String profile;


    @Schema(description = "审核状态")
    private Long auditStatusDictId;


    @Schema(description = "内容类型")
    private Long contentTypeDictId;


    @Schema(description = "图片地址")
    private String imageUrl;


    @Schema(description = "图片描述")
    private String imageDescription;


    @Schema(description = "图片地址1")
    private String imageUrl1;


    @Schema(description = "图片描述1")
    private String imageDescription1;


    @Schema(description = "图片地址2")
    private String imageUrl2;


    @Schema(description = "图片描述2")
    private String imageDescription2;


    @NotEmpty(message = "内容模板路径 不能为空")
        @Schema(description = "内容模板路径",requiredMode = Schema.RequiredMode.REQUIRED)
    private String templatePath;


    @NotEmpty(message = "内容模板 不能为空")
        @Schema(description = "内容模板",requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateIndex;


    @Schema(description = "内容静态页存放路径")
    private String staticPath;



    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;









}
