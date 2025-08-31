package com.particle.role.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class RoleVO extends AbstractBaseIdTreeVO {


    @Schema(description = "角色编码,模糊查询")
    private String code;

    @Schema(description = "角色名称,模糊查询")
    private String name;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "是否超级管理员")
    private Boolean isSuperadmin;

	@Schema(description = "角色类型，字典id")
	private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    @Schema(description = "角色类型，字典值")
    private String typeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "角色类型，字典名称")
    private String typeDictName;

    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_role, byFieldName = "parentId", mapValueField = "name")
    private String parentName;
}
