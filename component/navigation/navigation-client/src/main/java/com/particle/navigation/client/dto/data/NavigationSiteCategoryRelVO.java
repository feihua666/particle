package com.particle.navigation.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 导航网站分类关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Data
@Schema
public class NavigationSiteCategoryRelVO extends AbstractBaseIdVO {

    @Schema(description = "导航网站id")
    private Long navigationSiteId;

    @TransBy(tableName = TransTableNameConstants.component_navigation_site, byFieldName = "navigationSiteId", mapValueField = "name")
    @Schema(description = "导航网站名称")
    private String navigationSiteName;

    @Schema(description = "导航分类id")
    private Long navigationCategoryId;

    @TransBy(tableName = TransTableNameConstants.component_navigation_category, byFieldName = "navigationCategoryId", mapValueField = "name")
    @Schema(description = "导航分类id")
    private String navigationCategoryName;

    @Schema(description = "排序")
    private Integer seq;



}
