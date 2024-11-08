package com.particle.navigation.client.dto.command;

import cn.hutool.json.JSONUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Data
@Schema
public class NavigationSiteCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "网站名称 不能为空")
        @Schema(description = "网站名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "网站标题 不能为空")
        @Schema(description = "网站标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @Schema(description = "网站logo图标地址")
    private String logoUrl;


    @NotEmpty(message = "网站地址 不能为空")
        @Schema(description = "网站地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;


    @Schema(description = "网站截屏地址")
    private String screenshotUrl;


    @Schema(description = "网站截屏缩略图地址")
    private String screenshotThumbnailUrl;


    @Schema(description = "简短描述")
    private String content;


    @Schema(description = "详细描述")
    private String contentDetail;

	@Schema(description = "收录时间")
	private LocalDateTime collectionAt;

	@Schema(description = "是否已发布，已发布不能修改和删除")
	private Boolean isPublished;

	@Schema(description = "下架原因，未发布原因")
	private String unpublishedReason;


    @Schema(description = "描述")
    private String remark;


    public static NavigationSiteCreateCommand createFromJson(String json) {
        return JSONUtil.toBean(json, NavigationSiteCreateCommand.class);
    }

}
