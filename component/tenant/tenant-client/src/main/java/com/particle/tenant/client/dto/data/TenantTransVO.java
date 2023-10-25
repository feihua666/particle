package com.particle.tenant.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户 翻译结果
 * </p>
 *
 * @author yw
 * @since 2023-10-20 16:53:02
 */
@Accessors(chain = true)
@Data
@Schema
public class TenantTransVO extends AbstractBaseIdVO {

    @Schema(description = "租户编码")
    private String code;

    @Schema(description = "租户名称")
    private String name;
}
