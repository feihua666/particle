package com.particle.dataconstraint.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据对象 翻译结果
 * </p>
 *
 * @author yw
 * @since 2024-07-02 11:51:05
 */
@Accessors(chain = true)
@Data
@Schema
public class DataObjectTransVO extends AbstractBaseIdVO {

    @Schema(description = "数据对象编码")
    private String code;

    @Schema(description = "数据对象名称")
    private String name;
}
