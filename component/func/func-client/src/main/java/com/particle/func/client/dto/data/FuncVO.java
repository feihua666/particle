package com.particle.func.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 菜单功能 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@Schema
public class FuncVO extends AbstractBaseIdTreeVO {


    @Schema(description = "编码，模糊查询")
    private String code;

    @Schema(description = "名称，模糊查询")
    private String name;

    @Schema(description = "功能分组id")
    private Long funcGroupId;

    @Schema(description = "功能分组名称")
    @TransBy(tableName = TransTableNameConstants.component_func_group, byFieldName = "funcGroupId", mapValueField = "name")
    private String funcGroupName;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "地址")
    private String url;

    @Schema(description = "shiro权限串，多个以逗号分隔")
    private String permissions;

    @Schema(description = "类型,字典id")
    private Long typeDictId;

    @Schema(description = "类型,字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    private String typeDictName;

    @Schema(description = "类型,字典值")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    private String typeDictValue;

    @Schema(description = "是否展示")
    private Boolean isShow;

    @Schema(description = "归属组件")
    private String componentOf;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "parentId", mapValueField = "name")
    private String parentName;
}
