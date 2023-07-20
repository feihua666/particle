package com.particle.func.client.application.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class FuncApplicationVO extends AbstractBaseIdTreeVO {

    @Schema(description = "应用编码")
    private String code;
    
    @Schema(description = "功能应用名称")
    private String name;
    
    @Schema(description = "是否禁用")
    private Boolean isDisabled;
    
    @Schema(description = "禁用原因")
    private String disabledReason;
    
    @Schema(description = "应用主题")
    private String applicationTheme;
    
    @Schema(description = "应用默认的页面路由")
    private String applicationDefaultRoute;

    @Schema(description = "应用logo地址")
    private String applicationLogoUrl;

    @Schema(description = "应用图标地址")
    private String applicationIconUrl;

    @Schema(description = "额外配置json")
    private String configJson;
    
    @Schema(description = "是否为分组")
    private Boolean isGroup;
    
    @Schema(description = "排序")
    private Integer seq;
    
    @Schema(description = "描述")
    private String remark;


    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
