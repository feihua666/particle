package com.particle.func.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 功能菜单 翻译结果
 * </p>
 *
 * @author yw
 * @since 2022-12-27
 */
@Accessors(chain = true)
@Data
@Schema
public class FuncTransVO extends AbstractBaseIdVO {
    
    @Schema(description = "字典编码,模糊查询，字典组时必填")
    private String code;

    @Schema(description = "字典名称,模糊查询")
    private String name;

}
