package com.particle.dataquery.client.provider.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据查询供应商 翻译结果
 * </p>
 *
 * @author yw
 * @since 2023-08-17 10:12:24
 */
@Accessors(chain = true)
@Data
@Schema
public class DataQueryProviderTransVO extends AbstractBaseIdVO {

    @Schema(description = "供应商名称")
    private String name;

}
