package com.particle.data.client.dynamicdata.dto.data;

import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.global.light.share.trans.anno.TransField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 动态数据指标 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-27 20:48:18
 */
@Data
@Schema
public class DynamicDataIndicatorWithDynamicTableFieldVO extends DynamicDataIndicatorVO {

    @TransField
    @Schema(description = "动态数据表格字段")
    private DynamicTableFieldVO dynamicTableField;

}
