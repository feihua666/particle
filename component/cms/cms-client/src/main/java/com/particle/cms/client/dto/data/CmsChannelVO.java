package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 栏目 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Schema
public class CmsChannelVO extends AbstractBaseIdTreeVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @TransBy(tableName = TransTableNameConstants.component_cms_site, byFieldName = "cmsSiteId", mapValueField = "name")
    @Schema(description = "站点名称")
    private String cmsSiteName;

    @Schema(description = "栏目编码")
    private String code;

    @Schema(description = "栏目名称")
    private String name;

    @Schema(description = "栏目模板路径")
    private String templatePath;

    @Schema(description = "栏目模板")
    private String templateIndex;

    @Schema(description = "栏目静态页存放路径")
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
