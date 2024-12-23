package com.particle.dict.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典 翻译结果
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Accessors(chain = true)
@Data
@Schema
public class DictTransVO extends AbstractBaseIdVO {

    @Schema(description = "字典编码,模糊查询，字典组时必填")
    private String code;

    @Schema(description = "字典名称,模糊查询")
    private String name;

    @Schema(description = "字典值,模糊查询")
    private String value;
}
