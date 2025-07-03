package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 内容多媒体 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Data
@Schema
public class CmsContentMultimediaVO extends AbstractBaseIdVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @TransBy(tableName = TransTableNameConstants.component_cms_site, byFieldName = "cmsSiteId", mapValueField = "name")
    @Schema(description = "站点名称")
    private String cmsSiteName;

    @Schema(description = "内容id")
    private Long cmsContentId;

    @TransBy(tableName = TransTableNameConstants.component_cms_content, byFieldName = "cmsContentId", mapValueField = "title")
    @Schema(description = "内容标题")
    private String cmsContentTitle;

    @Schema(description = "文本内容")
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

    @Schema(description = "排序")
    private Integer seq;



}
