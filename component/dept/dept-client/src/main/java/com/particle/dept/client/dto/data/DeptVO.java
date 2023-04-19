package com.particle.dept.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class DeptVO extends AbstractBaseIdTreeVO {

    @ApiModelProperty("部门编码")
    private String code;
    
    @ApiModelProperty("部门名称")
    private String name;
    
    @ApiModelProperty("类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @ApiModelProperty("类型对应字典名称")
    private String typeDictName;
        
    @ApiModelProperty("负责人用户id")
    private Long masterUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "masterUserId",mapValueField = "name")
    @ApiModelProperty("负责人用户姓名")
    private Long masterUserName;
    
    @ApiModelProperty("是否虚拟部门")
    private Boolean isVirtual;
    
    @ApiModelProperty("是否为公司")
    private Boolean isComp;
    
    @ApiModelProperty("描述")
    private String remark;


    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_dept, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
