package com.particle.tenant.client.tenantfunc.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransTimes;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
@TransTimes
public class TenantFuncVO extends AbstractBaseIdVO {

    @Schema(description = "功能id")
    private Long funcId;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "name")
    @Schema(description = "名称")
    private String name;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "icon")
    @Schema(description = "图标")
    private String icon;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "typeDictId")
    @Schema(description = "类型,字典id")
    private Long typeDictId;

    /**
     * 第二次翻译有效
     */
    @Schema(description = "类型,字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    private String typeDictName;

    /**
     * 第二次翻译有效
     */
    @Schema(description = "类型,字典值")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    private String typeDictValue;

    @Schema(description = "租户id")
    private Long tenantId;

    @TransBy(tableName = TransTableNameConstants.component_tenant, byFieldName = "tenantId", mapValueField = "name")
    @Schema(description = "租户名称")
    private String tenantName;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "parentId")
    @Schema(description = "父级 parentFuncId")
    private Long parentFuncId;

    /**
     * 第二次翻译有效
     */
    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "parentFuncId", mapValueField = "name")
    private String parentFuncName;


    @Schema(description = "功能应用id")
    private Long funcApplicationId;

    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "funcApplicationId", mapValueField = "name")
    @Schema(description = "功能应用名称")
    private String funcApplicationName;
}
