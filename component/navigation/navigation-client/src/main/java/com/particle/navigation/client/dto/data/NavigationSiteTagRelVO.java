package com.particle.navigation.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 导航网站标签关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Data
@Schema
public class NavigationSiteTagRelVO extends AbstractBaseIdVO {

    @Schema(description = "网站id")
    private Long navigationSiteId;

    @TransBy(tableName = TransTableNameConstants.component_navigation_site, byFieldName = "navigationSiteId", mapValueField = "name")
    @Schema(description = "网站名称")
    private String navigationSiteName;

    @Schema(description = "网站标签id")
    private Long navigationSiteTagId;

    @TransBy(tableName = TransTableNameConstants.component_navigation_site_tag, byFieldName = "navigationSiteTagId", mapValueField = "name")
    @Schema(description = "网站标签名称")
    private String navigationSiteTagName;


}
