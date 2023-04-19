package com.particle.func.client.application.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 功能应用 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@ApiModel
public class FuncApplicationVO extends AbstractBaseIdTreeVO {

    @ApiModelProperty("应用编码")
    private String code;
    
    @ApiModelProperty("功能应用名称")
    private String name;
    
    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;
    
    @ApiModelProperty("禁用原因")
    private String disabledReason;
    
    @ApiModelProperty("应用主题")
    private String applicationTheme;
    
    @ApiModelProperty("应用默认的页面路由")
    private String applicationDefaultRoute;

    @ApiModelProperty("应用logo地址")
    private String applicationLogoUrl;

    @ApiModelProperty("应用图标地址")
    private String applicationIconUrl;

    @ApiModelProperty("额外配置json")
    private String configJson;
    
    @ApiModelProperty("是否为分组")
    private Boolean isGroup;
    
    @ApiModelProperty("排序")
    private Integer seq;
    
    @ApiModelProperty("描述")
    private String remark;


    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
