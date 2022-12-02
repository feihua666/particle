package com.particle.func.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 功能组 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@ApiModel
public class FuncGroupVO extends AbstractBaseIdVO {


    @ApiModelProperty("编码，模糊查询")
    private String code;

    @ApiModelProperty("名称，模糊查询")
    private String name;

    @ApiModelProperty("描述")
    private String remark;


}
