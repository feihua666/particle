package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码数据源 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@Schema
public class LowcodeDatasourceVO extends AbstractBaseIdVO {


    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "驱动类名")
    private String driverClassName;

    @Schema(description = "地址")
    private String url;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
