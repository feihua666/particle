package com.particle.tenant.client.tenantfuncapplication.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.global.light.share.trans.anno.TransTimes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 租户功能应用 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@ApiModel
@TransTimes
public class TenantFuncApplicationVO extends AbstractBaseIdVO {

    @ApiModelProperty("功能应用id")
    private Long funcApplicationId;

    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "funcApplicationId", mapValueField = "name")
    @ApiModelProperty("名称")
    private String name;

    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "funcApplicationId", mapValueField = "isGroup")
    @ApiModelProperty("是否为分组")
    private Boolean isGroup;

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


    @ApiModelProperty("租户id")
    private Long tenantId;

    @TransBy(tableName = TransTableNameConstants.component_tenant, byFieldName = "tenantId", mapValueField = "name")
    @ApiModelProperty("租户名称")
    private String tenantName;


    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "funcApplicationId", mapValueField = "parentId")
    @ApiModelProperty("父级 parentFuncApplicationId")
    private Long parentFuncApplicationId;

    /**
     * 第二次翻译有效
     */
    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "parentFuncApplicationId", mapValueField = "name")
    private String parentFuncApplicationName;
}
