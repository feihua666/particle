package com.particle.dept.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门树 翻译结果
 * 主要是为了解决根据 DeptTreeId 翻译部门名称
 * </p>
 *
 * @author yw
 * @since 2023-04-13 09:53:15
 */
@Accessors(chain = true)
@Data
@ApiModel
public class DeptTreeTransVO extends AbstractBaseIdVO {
    
    @ApiModelProperty("字典编码,模糊查询，字典组时必填")
    private Long deptId;

    @ApiModelProperty("字典名称,模糊查询")
    private String deptName;

}
