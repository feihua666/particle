package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 导航友情链接 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Data
@Schema
public class NavigationFriendshipLinkCreateCommand extends AbstractBaseCommand {



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


    @Schema(description = "收录时间")
    private LocalDateTime collectionAt;


    @NotNull(message = "是否已发布 不能为空")
        @Schema(description = "是否已发布",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPublished;


    @Schema(description = "下架原因")
    private String unpublishedReason;


    @Schema(description = "描述")
    private String remark;









}
