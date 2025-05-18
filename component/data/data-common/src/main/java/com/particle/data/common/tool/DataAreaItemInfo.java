package com.particle.data.common.tool;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 区域项信息，主要用来匹配一些区域项
 * </p>
 *
 * @author yangwei
 * @since 2025/5/9 11:03
 */
@Data
public class DataAreaItemInfo {

    @Schema(description = "区域id")
    private Long id;

    @Schema(description = "区域编码")
    private String code;

    @Schema(description = "区域名称")
    private String name;

    public static DataAreaItemInfo create(Long id, String code, String name) {
        DataAreaItemInfo dataDictItemInfo = new DataAreaItemInfo();
        dataDictItemInfo.setId(id);
        dataDictItemInfo.setCode(code);
        dataDictItemInfo.setName(name);
        return dataDictItemInfo;
    }
}
