package com.particle.tracking.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 埋点页面 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Data
@Schema
public class TrackingPageVO extends AbstractBaseIdTreeVO {

    @Schema(description = "页面编码")
    private String code;
    
    @Schema(description = "页面名称")
    private String name;
    
    @Schema(description = "页面图片地址")
    private String imageUrl;
    
    @Schema(description = "页面访问地址")
    private String absoluteUrl;
    
    @Schema(description = "路径说明")
    private String pathMemo;
    
    @Schema(description = "页面版本")
    private String pageVersion;
    
    @Schema(description = "分组标识")
    private String groupFlag;
    
    @Schema(description = "排序")
    private Integer seq;
    
    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_tracking_page, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
