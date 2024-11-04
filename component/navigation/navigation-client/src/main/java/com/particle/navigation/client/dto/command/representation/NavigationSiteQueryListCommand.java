package com.particle.navigation.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;

import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Data
@Schema
public class NavigationSiteQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "网站名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "网站标题,左前缀匹配")
    private String title;


    @Schema(description = "网站logo图标地址")
    private String logoUrl;


    @Schema(description = "网站地址")
    private String url;


    @Schema(description = "网站截屏地址")
    private String screenshotUrl;


    @Schema(description = "网站截屏缩略图地址")
    private String screenshotThumbnailUrl;


    @Schema(description = "简短描述")
    private String content;


    @Schema(description = "详细描述")
    private String contentDetail;

	@Schema(description = "收费情况，字典id")
	private Long feeSituationDictId;

	@Schema(description = "收录时间")
	private LocalDateTime collectionAt;

	@Schema(description = "是否已发布，已发布不能修改和删除")
	private Boolean isPublished;

	@Schema(description = "下架原因，未发布原因")
	private String unpublishedReason;










}