package com.particle.dataquery.domain.value;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放平台供应商对应的信息
 * </p>
 *
 * @author yangwei
 * @since 2025/5/8 12:43
 */
@Data
public class DataQueryOpenplatformProvider {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "供应商名称")
    private String name;


    public static DataQueryOpenplatformProvider create(Long id,String code, String name) {
        DataQueryOpenplatformProvider dataQueryOpenplatformProvider = new DataQueryOpenplatformProvider();
        dataQueryOpenplatformProvider.id = id;
        dataQueryOpenplatformProvider.code = code;
        dataQueryOpenplatformProvider.name = name;
        return dataQueryOpenplatformProvider;
    }
}
