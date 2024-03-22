package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数字典
 * </p>
 *
 * @author yw
 * @since 2024-03-22 10:49:28
 */
@Accessors(chain = true)
@Data
@Schema
public class OpenplatformDocParamFieldDictItemVO extends AbstractBaseIdVO {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典值")
    private String value;

    public static OpenplatformDocParamFieldDictItemVO create(String name, String value) {
        OpenplatformDocParamFieldDictItemVO openplatformDocParamFieldDictItemVO = new OpenplatformDocParamFieldDictItemVO();
        openplatformDocParamFieldDictItemVO.name = name;
        openplatformDocParamFieldDictItemVO.value = value;
        return openplatformDocParamFieldDictItemVO;
    }
}
