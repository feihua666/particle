package com.particle.openplatform.domain.doc.value;

import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/3/22 11:26
 */
@Data
public class OpenplatformDocParamFieldDictItemParam extends Value {

    /**
     * 字典组id
     */
    private Long dictGroupId;
    /**
     * 标签，过滤标签,可根据标签过滤，多个以逗号分隔
     */
    private String tags;

    public static OpenplatformDocParamFieldDictItemParam create(Long dictGroupId, String tags) {
        OpenplatformDocParamFieldDictItemParam openplatformDocParamFieldDictItemParam = new OpenplatformDocParamFieldDictItemParam();
        openplatformDocParamFieldDictItemParam.dictGroupId = dictGroupId;
        openplatformDocParamFieldDictItemParam.tags = tags;
        return openplatformDocParamFieldDictItemParam;
    }
}
