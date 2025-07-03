package com.particle.cms.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 内容 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Schema
public class CmsContentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "站点id")
    private Long cmsSiteId;


    @Schema(description = "栏目id")
    private Long cmsChannelId;


    @Schema(description = "内容分类id")
    private Long cmsContentCategoryId;


    @Like
    @Schema(description = "标题，左前缀匹配")
    private String title;

    @Like
    @Schema(description = "作者，左前缀匹配")
    private String author;

    @Like
    @Schema(description = "来源，左前缀匹配")
    private String original;




    @Schema(description = "简介")
    private String profile;


    @Schema(description = "审核状态")
    private Long auditStatusDictId;


    @Schema(description = "是否发布")
    private Boolean isPublic;


    @Schema(description = "发布时间")
    private LocalDateTime publicAt;


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


    @Schema(description = "内容模板路径")
    private String templatePath;


    @Schema(description = "内容模板")
    private String templateIndex;


    @Schema(description = "内容静态页存放路径")
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
