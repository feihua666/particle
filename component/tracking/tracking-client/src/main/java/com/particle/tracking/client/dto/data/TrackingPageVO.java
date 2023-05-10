package com.particle.tracking.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class TrackingPageVO extends AbstractBaseIdTreeVO {

    @ApiModelProperty("页面编码")
    private String code;
    
    @ApiModelProperty("页面名称")
    private String name;
    
    @ApiModelProperty("页面图片地址")
    private String imageUrl;
    
    @ApiModelProperty("页面访问地址")
    private String absoluteUrl;
    
    @ApiModelProperty("路径说明")
    private String pathMemo;
    
    @ApiModelProperty("页面版本")
    private String pageVersion;
    
    @ApiModelProperty("分组标识")
    private String groupFlag;
    
    @ApiModelProperty("排序")
    private Integer seq;
    
    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_tracking_page, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
