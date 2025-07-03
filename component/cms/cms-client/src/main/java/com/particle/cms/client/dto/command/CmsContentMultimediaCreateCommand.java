package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 内容多媒体 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Data
@Schema
public class CmsContentMultimediaCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "站点id 不能为空")
        @Schema(description = "站点id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long cmsSiteId;


    @Schema(description = "栏目id")
    private Long cmsContentId;


    @NotEmpty(message = "文本内容 不能为空")
        @Schema(description = "文本内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;


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


    @Schema(description = "文件名称")
    private String fileName;


    @Schema(description = "文件地址")
    private String fileUrl;


    @Schema(description = "文件大小")
    private Integer fileSize;


    @Schema(description = "文件大小字符串")
    private String fileSizeStr;


    @Schema(description = "媒体类型")
    private String mediaType;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;









}
