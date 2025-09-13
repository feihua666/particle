package com.particle.component.light.share.role;

import com.particle.global.dto.basic.VO;
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
public class RoleTransVO extends VO {

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色名称")
    private String name;
}
