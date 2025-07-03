package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 内容分类 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Data
@Schema
public class CmsContentCategoryVO extends AbstractBaseIdTreeVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @TransBy(tableName = TransTableNameConstants.component_cms_site, byFieldName = "cmsSiteId", mapValueField = "name")
    @Schema(description = "站点名称")
    private String cmsSiteName;

    @Schema(description = "栏目id")
    private Long cmsChannelId;

    @TransBy(tableName = TransTableNameConstants.component_cms_channel, byFieldName = "cmsChannelId", mapValueField = "name")
    @Schema(description = "栏目名称")
    private String cmsChannelName;

    @Schema(description = "分类名称")
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
