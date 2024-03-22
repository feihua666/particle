package com.particle.openplatform.domain.doc.value;

import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * 字典数据
 * </p>
 *
 * @author yangwei
 * @since 2024/3/22 11:06
 */
@Data
public class OpenplatformDocParamFieldDictItemDTO extends Value {

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

    public static OpenplatformDocParamFieldDictItemDTO create(String name, String value) {
        OpenplatformDocParamFieldDictItemDTO openplatformDocParamFieldDictItemDTO = new OpenplatformDocParamFieldDictItemDTO();
        openplatformDocParamFieldDictItemDTO.name = name;
        openplatformDocParamFieldDictItemDTO.value = value;
        return openplatformDocParamFieldDictItemDTO;
    }
}
