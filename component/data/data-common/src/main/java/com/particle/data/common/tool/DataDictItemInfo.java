package com.particle.data.common.tool;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 字典项信息，主要用来匹配一些字典项
 * </p>
 *
 * @author yangwei
 * @since 2025/5/9 11:03
 */
@Data
public class DataDictItemInfo {

    @Schema(description = "字典id")
    private Long id;

    @Schema(description = "字典编码")
    private String code;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典值")
    private String value;

    public static DataDictItemInfo create(Long id,String code, String name, String value) {
        DataDictItemInfo dataDictItemInfo = new DataDictItemInfo();
        dataDictItemInfo.setId(id);
        dataDictItemInfo.setCode(code);
        dataDictItemInfo.setName(name);
        dataDictItemInfo.setValue(value);
        return dataDictItemInfo;
    }
}
