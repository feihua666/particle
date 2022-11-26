package com.particle.role.client.rolefuncrel.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色菜单功能关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleFuncRelVO extends AbstractBaseIdVO {


    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("功能id")
    private Long funcId;


}
