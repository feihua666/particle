package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class LowcodeDatasourceVO extends AbstractBaseIdVO {


    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("驱动类名")
    private String driverClassName;

    @ApiModelProperty("地址")
    private String url;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("描述,注意事项等")
    private String remark;


}
