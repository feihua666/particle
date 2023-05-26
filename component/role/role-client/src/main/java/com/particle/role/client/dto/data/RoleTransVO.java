package com.particle.role.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色 翻译结果
 * </p>
 *
 * @author yw
 * @since 2023-04-13 09:53:15
 */
@Accessors(chain = true)
@Data
@ApiModel
public class RoleTransVO extends AbstractBaseIdVO {

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色名称")
    private String name;
}
