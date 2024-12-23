package com.particle.dept.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 部门 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@Schema
public class DeptVO extends AbstractBaseIdTreeVO {

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    @Schema(description = "类型对应字典值")
    private String typeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型对应字典名称")
    private String typeDictName;

    @Schema(description = "负责人用户id")
    private Long masterUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "masterUserId",mapValueField = "name")
    @Schema(description = "负责人用户姓名")
    private String masterUserName;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "masterUserId",mapValueField = "nickname")
    @Schema(description = "负责人用户昵称")
    private String masterUserNickname;

    @Schema(description = "是否虚拟部门")
    private Boolean isVirtual;

    @Schema(description = "是否为公司")
    private Boolean isComp;

    @Schema(description = "描述")
    private String remark;


    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_dept, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
