package com.particle.func.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 功能组 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@Schema
public class FuncGroupVO extends AbstractBaseIdVO {


    @Schema(description = "编码，模糊查询")
    private String code;

    @Schema(description = "名称，模糊查询")
    private String name;

    @Schema(description = "描述")
    private String remark;


}
