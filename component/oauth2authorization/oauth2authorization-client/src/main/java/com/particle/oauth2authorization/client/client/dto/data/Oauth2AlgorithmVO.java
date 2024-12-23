package com.particle.oauth2authorization.client.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * oauth2算法 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2023-07-27 15:48:36
 */
@Data
@Schema
public class Oauth2AlgorithmVO extends AbstractBaseVO {

    @Schema(description = "算法名称")
    private String name;

    @Schema(description = "算法值，具体的算法")
    private String value;


    public static Oauth2AlgorithmVO create(String name, String value) {
        Oauth2AlgorithmVO oauth2AlgorithmVO = new Oauth2AlgorithmVO();
        oauth2AlgorithmVO.setName(name);
        oauth2AlgorithmVO.setValue(value);
        return oauth2AlgorithmVO;
    }

    public static Oauth2AlgorithmVO createByAlgorithmStr(String value) {
        return create(value, value);
    }
    public static List<Oauth2AlgorithmVO> createByAlgorithmStrs(List<String> values) {
        return values.stream().map(value -> createByAlgorithmStr(value)).collect(Collectors.toList());
    }
}
