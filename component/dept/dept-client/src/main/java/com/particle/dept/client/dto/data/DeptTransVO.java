package com.particle.dept.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门 翻译结果
 * </p>
 *
 * @author yw
 * @since 2023-04-13 09:53:15
 */
@Accessors(chain = true)
@Data
@ApiModel
public class DeptTransVO extends AbstractBaseIdVO {
    @ApiModelProperty("部门名称")
    private String name;
}
