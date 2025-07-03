package com.particle.cms.client.dto.command.representation;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 内容分类 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Data
@Schema
public class CmsContentCategoryPageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Schema(description = "站点id")
    private Long cmsSiteId;


    @Schema(description = "栏目id")
    private Long cmsChannelId;


    @Like
    @Schema(description = "分类名称，左前缀匹配")
    private String name;


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


    @Schema(description = "排序")
    private Integer seq;





















}
