package com.particle.tenant.client.tenantfunc.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransTimes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 租户功能菜单 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@ApiModel
@TransTimes
public class TenantFuncVO extends AbstractBaseIdVO {

    @ApiModelProperty("功能id")
    private Long funcId;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "name")
    @ApiModelProperty("名称")
    private String name;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "icon")
    @ApiModelProperty("图标")
    private String icon;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "typeDictId")
    @ApiModelProperty("类型,字典id")
    private Long typeDictId;

    /**
     * 第二次翻译有效
     */
    @ApiModelProperty("类型,字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    private String typeDictName;

    /**
     * 第二次翻译有效
     */
    @ApiModelProperty("类型,字典值")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    private String typeDictValue;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @TransBy(tableName = TransTableNameConstants.component_tenant, byFieldName = "tenantId", mapValueField = "name")
    @ApiModelProperty("租户名称")
    private String tenantName;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "parentId")
    @ApiModelProperty("父级 parentFuncId")
    private Long parentFuncId;

    /**
     * 第二次翻译有效
     */
    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "parentFuncId", mapValueField = "name")
    private String parentFuncName;


    @ApiModelProperty("功能应用id")
    private Long funcApplicationId;

    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "funcApplicationId", mapValueField = "name")
    @ApiModelProperty("功能应用名称")
    private String funcApplicationName;
}
