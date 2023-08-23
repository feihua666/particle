package com.particle.openplatform.client.app.dto.data;

import com.particle.common.client.dto.data.AbstractBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 开放平台算法 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-07 16:42:13
 */
@Data
@Schema
public class OpenplatformAlgorithmVO extends AbstractBaseVO {

    @Schema(description = "算法名称")
    private String name;

    @Schema(description = "算法值，具体的算法")
    private String value;


    public static OpenplatformAlgorithmVO create(String name, String value) {
        OpenplatformAlgorithmVO openplatformAlgorithmVO = new OpenplatformAlgorithmVO();
        openplatformAlgorithmVO.setName(name);
        openplatformAlgorithmVO.setValue(value);
        return openplatformAlgorithmVO;
    }

    public static OpenplatformAlgorithmVO createByAlgorithmStr(String value) {
        return create(value, value);
    }
    public static List<OpenplatformAlgorithmVO> createByAlgorithmStrs(List<String> values) {
        return values.stream().map(value -> createByAlgorithmStr(value)).collect(Collectors.toList());
    }
}
