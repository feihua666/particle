package com.particle.role.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class RoleTransVO extends AbstractBaseIdVO {

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色名称")
    private String name;
}
