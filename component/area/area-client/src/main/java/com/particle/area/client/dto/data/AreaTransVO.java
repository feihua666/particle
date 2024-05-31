package com.particle.area.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域 翻译结果
 * </p>
 *
 * @author yw
 * @since 2024-05-31 13:00:24
 */
@Accessors(chain = true)
@Data
@Schema
public class AreaTransVO extends AbstractBaseIdVO {

    @Schema(description = "编码，唯一,模糊查询")
    private String code;

    @Schema(description = "区域名称,模糊查询")
    private String name;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;
}
