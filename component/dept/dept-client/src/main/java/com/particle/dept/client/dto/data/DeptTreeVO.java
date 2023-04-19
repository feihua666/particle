package com.particle.dept.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 部门树 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Data
@ApiModel
public class DeptTreeVO extends AbstractBaseIdTreeVO {

    @ApiModelProperty("部门id")
    private Long deptId;

    @TransBy(tableName = TransTableNameConstants.component_dept, byFieldName = "deptId", mapValueField = "name")
    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("部门树名称id")
    private Long deptTreeNameId;

    @TransBy(tableName = TransTableNameConstants.component_dept_tree_name, byFieldName = "deptTreeNameId", mapValueField = "name")
    @ApiModelProperty("部门树名称名称")
    private String deptTreeNameName;
    
    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("父级名称")
    @TransBy(type = TransConstants.TRANS_DEPT_TREE_BY_ID, byFieldName = "parentId", mapValueField = "deptName")
    private String parentName;
}
