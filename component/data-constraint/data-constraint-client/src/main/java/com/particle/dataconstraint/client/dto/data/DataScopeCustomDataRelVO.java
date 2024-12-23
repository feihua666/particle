package com.particle.dataconstraint.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据范围自定义数据关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Data
@Schema
public class DataScopeCustomDataRelVO extends AbstractBaseIdVO {

    @Schema(description = "数据范围id")
    private Long dataScopeId;

    @Schema(description = "自定义数据id")
    private Long dataId;



}
